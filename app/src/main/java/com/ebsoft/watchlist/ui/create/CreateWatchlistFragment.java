package com.ebsoft.watchlist.ui.create;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.FragmentCreateWatchlistBinding;
import com.ebsoft.watchlist.ui.base.BaseFragment;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class CreateWatchlistFragment extends BaseFragment<FragmentCreateWatchlistBinding, CreateWatchlistViewModel>
        implements CreateWatchlistNavigator{

    @Inject
    CreateWatchlistViewModel mCreateWatchlistViewModel;

    @Override
    public void setup() { mCreateWatchlistViewModel.setNavigator(this); }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_create_watchlist;
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
        Navigation.findNavController(getView()).navigateUp();
    }
}
