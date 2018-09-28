package com.ebsoft.watchlist.ui.main;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.ActivityMainBinding;
import com.ebsoft.watchlist.di.MainActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.create.CreateWatchlistActivity;
import com.ebsoft.watchlist.ui.watchlist.WatchlistActivity;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator, CardViewItemClickListener<Watchlist> {

    @Inject
    MainViewModel mMainViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Inject
    @MainActivityQualifier
    LinearLayoutManager mLayoutManager;

    @Override
    public void setup() {
        ActivityMainBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.mainActivityRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.mainActivityRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        getViewModel().loadWatchlists(this);
        mAdapter.setItemClickListener(this);
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
}
