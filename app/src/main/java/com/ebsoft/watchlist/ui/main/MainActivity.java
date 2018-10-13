package com.ebsoft.watchlist.ui.main;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivityMainBinding;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.ui.watchlists.WatchlistsViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, WatchlistsViewModel> {

    @Override
    public void setup() {}

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public WatchlistsViewModel getViewModel() {
        return null;
    }
}
