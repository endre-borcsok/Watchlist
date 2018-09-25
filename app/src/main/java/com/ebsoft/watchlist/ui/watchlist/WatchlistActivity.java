package com.ebsoft.watchlist.ui.watchlist;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.ActivityWatchlistBinding;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.search.SearchActivity;
import com.ebsoft.watchlist.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class WatchlistActivity extends BaseActivity<ActivityWatchlistBinding, WatchlistViewModel>
        implements WatchlistNavigator, SymbolListener {

    private final int RESULT_CODE = 0;

    @Inject
    WatchlistViewModel mWatchlistViewModel;

    @Inject
    StockAdapter mAdapter;

    @Inject
    @WatchlistActivityQualifier
    LinearLayoutManager mLayoutManager;

    @Override
    public void setup() {
        ActivityWatchlistBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.watchlistRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.watchlistRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.watchlistRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        getViewModel().loadStocks(this, (Watchlist) getIntent()
                .getSerializableExtra(Constants.EXTRA_KEY_WATCHLIST));
        mAdapter.setSymbolListener(this);
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

    @Override
    public void onActionButtonClick() {
        startActivityForResult(new Intent(this, SearchActivity.class), RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CODE) {
            if(resultCode == Activity.RESULT_OK){
                String result = data.getStringExtra(Constants.SEARCH_RESULT_KEY);
                Watchlist watchlist = (Watchlist) getIntent()
                        .getSerializableExtra(Constants.EXTRA_KEY_WATCHLIST);
                Stock stock = new Stock(result, watchlist.getName());
                getViewModel().insertStock(stock);
            }
        }
    }

    @Override
    public void onStockSelected(Stock stock) {
        getViewModel().getQuote(stock);
    }
}
