package com.ebsoft.watchlist.ui.search;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityMainBinding;
import com.ebsoft.watchlist.databinding.ActivitySearchBinding;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.base.BaseViewModel;
import com.ebsoft.watchlist.ui.main.WatchlistAdapter;

import javax.inject.Inject;
import javax.inject.Named;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    private SearchViewModel mSearchViewModel;

    @Inject
    @Named("SearchActivity")
    WatchlistAdapter mAdapter;

    @Inject
    @Named("SearchActivity")
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        mSearchViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(SearchViewModel.class);
        return mSearchViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ActivitySearchBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.searchRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.searchRecyclerView.setAdapter(mAdapter);
    }
}
