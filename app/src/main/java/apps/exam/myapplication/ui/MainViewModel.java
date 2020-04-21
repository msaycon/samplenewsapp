package apps.exam.myapplication.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import apps.exam.myapplication.BuildConfig;
import apps.exam.myapplication.repository.data.Article;
import apps.exam.myapplication.repository.data.Response;
import apps.exam.myapplication.repository.data.Source;
import apps.exam.myapplication.repository.retrofit.RepoRepository;
import apps.exam.myapplication.repository.room.RoomData;
import apps.exam.myapplication.repository.room.entities.LocalArticle;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import solid.functions.Action1;
import solid.stream.Stream;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class MainViewModel extends ViewModel {

    private static final String COUNTRY = "us";

    private static final String CATEGORY = "business";

    private final RepoRepository repoRepository;

    private CompositeDisposable disposable;

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();

    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    LiveData<List<Article>> getArticles() {
        return articles;
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    @Inject
    RoomData mRoomData;

    @Inject
    public MainViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
    }

    void getLocalHeadlines() {
        loading.setValue(true);
        disposable.add(mRoomData.getDatabaseDao().getAllArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(localArticles -> {
                    loading.setValue(false);
                    List<Article> articleList = new ArrayList<>();
                    Stream.stream(localArticles)
                            .map(this::getArticle)
                            .forEach((Action1<Article>) articleList::add);
                    articles.setValue(articleList);
                }));
    }

    void getRemoteHeadlines() {
        disposable.add(repoRepository.getNewsHeadlines(COUNTRY, CATEGORY, BuildConfig.NEWS_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response>() {
                    @Override
                    public void onSuccess(Response value) {
                        repoLoadError.setValue(false);
                        loading.setValue(false);
                        disposable.add(Flowable.fromIterable(value.articles)
                                .subscribe(article ->
                                        disposable.add(insertArticle(getLocalArticle(article))
                                                .subscribe())));
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    private Completable insertArticle(LocalArticle localArticle) {
        return mRoomData.getDatabaseDao().insertLocalArticle(localArticle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private LocalArticle getLocalArticle(Article article) {
        Source source = article.source;
        String id;
        if (source.id == null) {
            id = source.name.toLowerCase();
        } else {
            id = source.id;
        }
        return new LocalArticle(id
                , article.title
                , article.description
                , article.url
                , article.urlToImage
                , article.publishedAt);
    }

    private Article getArticle(LocalArticle article) {
        Source source = new Source(article.uid, article.uid.toUpperCase());
        return new Article(source, article.title
                , article.description
                , article.url
                , article.urlToImage
                , article.publishedAt);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
