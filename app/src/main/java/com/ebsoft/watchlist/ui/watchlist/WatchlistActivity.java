package com.ebsoft.watchlist.ui.watchlist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.ActivityWatchlistBinding;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.adapter.CardViewItemRemoveListener;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.search.SearchActivity;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

public class WatchlistActivity extends BaseActivity<ActivityWatchlistBinding, WatchlistViewModel>
        implements WatchlistNavigator,
        CardViewItemClickListener<Stock>,
        CardViewItemRemoveListener<Stock> {

    private final int RESULT_CODE = 0;

    @Inject
    WatchlistViewModel mWatchlistViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Inject
    @WatchlistActivityQualifier
    LinearLayoutManager mLayoutManager;

    private Watchlist mWatchlist;

    @Override
    public void setup() {
        mWatchlist = (Watchlist) getIntent().getSerializableExtra(Constants.EXTRA_KEY_WATCHLIST);
        mAdapter.setItemClickListener(this);
        mAdapter.setItemRemoveListener(this);
        ActivityWatchlistBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.watchlistRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.watchlistRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.watchlistRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        getViewModel().loadStocks(this, mWatchlist);
        setTitle(mWatchlist.getName());
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
    public void onItemClick(Stock item) {
        getViewModel().getQuote(item);
    }

    @Override
    public void onRemove(Stock item) {
        getViewModel().removeStock(item);
    }
}
