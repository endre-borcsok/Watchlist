package com.ebsoft.watchlist.ui.create;

import android.os.Bundle;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityCreateWatchlistBinding;
import com.ebsoft.watchlist.ui.base.BaseActivity;

import javax.inject.Inject;

public class CreateWatchlistActivity extends BaseActivity<ActivityCreateWatchlistBinding, CreateWatchlistViewModel>
        implements CreateWatchlistNavigator{

    @Inject
    CreateWatchlistViewModel mCreateWatchlistViewModel;

    @Override
    public void setup() { mCreateWatchlistViewModel.setNavigator(this); }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_watchlist;
    }

    @Override
    public CreateWatchlistViewModel getViewModel() {
        return mCreateWatchlistViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onWatchlistCreated() {
        finish();
    }
}
