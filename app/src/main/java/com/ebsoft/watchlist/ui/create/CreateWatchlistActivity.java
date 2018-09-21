package com.ebsoft.watchlist.ui.create;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityCreateWatchlistBinding;
import com.ebsoft.watchlist.ui.base.BaseActivity;

import javax.inject.Inject;

public class CreateWatchlistActivity extends BaseActivity<ActivityCreateWatchlistBinding, CreateWatchlistViewModel>
        implements CreateWatchlistNavigator{

    private CreateWatchlistViewModel mCreateWatchlistViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_watchlist;
    }

    @Override
    public CreateWatchlistViewModel getViewModel() {
        mCreateWatchlistViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(CreateWatchlistViewModel.class);
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

    private void setUp() {
        mCreateWatchlistViewModel.setNavigator(this);
    }
}
