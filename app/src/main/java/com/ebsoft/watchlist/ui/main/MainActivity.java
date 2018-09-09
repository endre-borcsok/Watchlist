package com.ebsoft.watchlist.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityMainBinding;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.search.SearchActivity;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    private MainViewModel mMainViewModel;

    @Inject
    @Named("MainActivity")
    WatchlistAdapter mAdapter;

    @Inject
    @Named("MainActivity")
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    private void setUp() {
        ActivityMainBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.mainActivityRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.mainActivityRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        getViewModel().loadWatchlists();
    }

    @Override
    public void onActionButtonClick() {
        startActivity(new Intent(this, SearchActivity.class));
    }
}
