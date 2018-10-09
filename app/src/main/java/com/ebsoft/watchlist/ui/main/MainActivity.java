package com.ebsoft.watchlist.ui.main;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.ActivityMainBinding;
import com.ebsoft.watchlist.di.MainActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.adapter.CardViewItemRemoveListener;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.create.CreateWatchlistActivity;
import com.ebsoft.watchlist.ui.watchlist.WatchlistActivity;
import com.ebsoft.watchlist.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator,
        CardViewItemClickListener<Watchlist>,
        CardViewItemRemoveListener<Watchlist> {

    @Inject
    MainViewModel mMainViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Inject
    @MainActivityQualifier
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void setup() {
        ActivityMainBinding viewDataBinding = getViewDataBinding();
        viewDataBinding.mainActivityRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.mainActivityRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        getViewModel().subscribeToLiveData(this);
        mAdapter.setItemClickListener(this);
        mAdapter.setItemRemoveListener(this);
    }

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
        return mMainViewModel;
    }

    @Override
    public void onActionButtonClick() {
        startActivity(new Intent(this, CreateWatchlistActivity.class));
    }

    @Override
    public void onItemClick(Watchlist item) {
        Intent intent = new Intent(this, WatchlistActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_WATCHLIST, item);
        startActivity(intent);
    }

    @Override
    public void onRemove(Watchlist item) {
        getViewModel().deleteWatchlist(item);
    }
}
