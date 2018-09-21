package com.ebsoft.watchlist.ui.watchlist;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityWatchlistBinding;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.base.BaseActivity;

import javax.inject.Inject;

public class WatchlistActivity extends BaseActivity<ActivityWatchlistBinding, WatchlistViewModel> {

    @Inject
    WatchlistViewModel mWatchlistViewModel;

    @Inject
    SymbolAdapter mAdapter;

    @Inject
    @WatchlistActivityQualifier
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_watchlist;
    }

    @Override
    public WatchlistViewModel getViewModel() {
        return mWatchlistViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    private void setUp() {
        ActivityWatchlistBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.watchlistActivityRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.watchlistActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.watchlistActivityRecyclerView.setAdapter(mAdapter);
    }
}
