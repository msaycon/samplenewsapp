package apps.exam.myapplication.ui;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import apps.exam.myapplication.BuildConfig;
import apps.exam.myapplication.repository.data.Article;
import apps.exam.myapplication.repository.data.Response;
import apps.exam.myapplication.repository.retrofit.RepoRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
    public MainViewModel(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
        disposable = new CompositeDisposable();
        getHeadlines();
    }

    private void getHeadlines() {
        loading.setValue(true);
        disposable.add(repoRepository.getNewsHeadlines(COUNTRY, CATEGORY, BuildConfig.NEWS_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Response>() {
                    @Override
                    public void onSuccess(Response value) {
                        repoLoadError.setValue(false);
                        articles.setValue(value.articles);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
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
