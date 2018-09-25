package com.ebsoft.watchlist.ui.main;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.ActivityMainBinding;
import com.ebsoft.watchlist.di.MainActivityQualifier;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.create.CreateWatchlistActivity;
import com.ebsoft.watchlist.ui.watchlist.WatchlistActivity;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator, WatchlistListener {

    @Inject
    MainViewModel mMainViewModel;

    @Inject
    WatchlistAdapter mAdapter;

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
        mAdapter.setWatchlistListener(this);
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
    public void subscribeForLiveData() {}

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().loadWatchlists();
    }

    @Override
    public void onActionButtonClick() {
        startActivity(new Intent(this, CreateWatchlistActivity.class));
    }

    @Override
    public void onWatchlistSelected(Watchlist watchlist) {
        Intent intent = new Intent(this, WatchlistActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_WATCHLIST, watchlist);
        startActivity(intent);
    }
}
