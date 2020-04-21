package apps.exam.myapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import apps.exam.myapplication.R;
import apps.exam.myapplication.base.BaseFragment;
import apps.exam.myapplication.di.viewmodel.ViewModelFactory;
import apps.exam.myapplication.repository.data.Article;
import apps.exam.myapplication.ui.itemview.ArticleView;
import butterknife.BindView;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import solid.functions.Action1;
import solid.stream.Stream;

/**
 * Created by msaycon on 21,Apr,2020
 */
public class MainFragment extends BaseFragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private MainViewModel mMainViewModel;

    private FlexibleAdapter<ArticleView> mAdapter;

    @BindView(R.id.itemListView)
    RecyclerView mRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mMainViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel.class);

        setUpViews();
        initObservers();

        mMainViewModel.getLocalHeadlines();
        mMainViewModel.getRemoteHeadlines();
    }

    private void setUpViews() {
        mAdapter = new FlexibleAdapter<>(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initObservers() {
        mMainViewModel.getArticles().observe(getViewLifecycleOwner(), articles -> {
            if (articles != null) {
                Stream.stream(articles)
                        .forEach((Action1<Article>) value -> {
                            ArticleView articleView = new ArticleView(value);
                            if (!mAdapter.contains(articleView)) {
                                mAdapter.addItem(mAdapter.calculatePositionFor(articleView,
                                        new ArticleView.ViewComparator()),
                                        articleView);
                            } else {
                                mAdapter.updateItem(articleView);
                            }
                        });
            }
        });

        mMainViewModel.getError().observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) {
                if (isError) {
                    Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mMainViewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                if (isLoading) {
                    mRecyclerView.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
