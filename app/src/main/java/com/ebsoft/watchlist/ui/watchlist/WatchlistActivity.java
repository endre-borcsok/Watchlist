package com.ebsoft.watchlist.ui.watchlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Symbol;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.ActivityWatchlistBinding;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.search.SearchActivity;

import javax.inject.Inject;

public class WatchlistActivity extends BaseActivity<ActivityWatchlistBinding, WatchlistViewModel>
        implements WatchlistNavigator, SymbolListener {

    public static final String EXTRA_KEY_WATCHLIST = "watchlistKey";
    private final int RESULT_CODE = 0;

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
    public void onResume() {
        super.onResume();
        Watchlist watchlist = (Watchlist) getIntent().getSerializableExtra(EXTRA_KEY_WATCHLIST);
        mWatchlistViewModel.loadSymbols(watchlist);
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
        startActivityForResult(new Intent(this, SearchActivity.class), RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra(SearchActivity.RESULT_KEY);
                Watchlist watchlist = (Watchlist) getIntent()
                        .getSerializableExtra(EXTRA_KEY_WATCHLIST);
                Symbol symbol = new Symbol(result, watchlist.name);
                getViewModel().insertSymbol(symbol);
            }
        }
    }

    @Override
    public void onSymbolSelected(String symbol) {
        Log.d("D", symbol);
    }
}
