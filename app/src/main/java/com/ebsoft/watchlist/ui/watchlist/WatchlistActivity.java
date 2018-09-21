package com.ebsoft.watchlist.ui.watchlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityWatchlistBinding;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.search.SearchActivity;

import javax.inject.Inject;

public class WatchlistActivity extends BaseActivity<ActivityWatchlistBinding, WatchlistViewModel>
        implements WatchlistNavigator, SymbolListener {

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
        viewDataBinding.watchlistRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.watchlistRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.watchlistRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        mAdapter.setSymbolListener(this);
    }

    @Override
    public void onActionButtonClick() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public void onSymbolSelected(String symbol) {
        Log.d("D", symbol);
    }
}
