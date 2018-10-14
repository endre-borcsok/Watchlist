package com.ebsoft.watchlist.ui.watchlists;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.FragmentWatchlistsBinding;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.adapter.CardViewItemRemoveListener;
import com.ebsoft.watchlist.ui.base.BaseFragment;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class WatchlistsFragment extends BaseFragment <FragmentWatchlistsBinding, WatchlistsViewModel>
        implements WatchlistsNavigator,
        CardViewItemClickListener<Watchlist>,
        CardViewItemRemoveListener<Watchlist> {

    @Inject
    WatchlistsViewModel mWatchlistsViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_watchlists;
    }

    @Override
    public WatchlistsViewModel getViewModel() {
        return mWatchlistsViewModel;
    }

    @Override
    public void setup() {
        FragmentWatchlistsBinding viewDataBinding = getViewDataBinding();
        viewDataBinding.mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.mainActivityRecyclerView.setAdapter(mAdapter);
        viewDataBinding.mainActivityRecyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewModel().setNavigator(this);
        getViewModel().subscribeToLiveData(this);
        mAdapter.setItemClickListener(this);
        mAdapter.setItemRemoveListener(this);
    }

    @Override
    public void onActionButtonClick() {
        Navigation.findNavController(getView()).navigate(R.id.createWatchlistFragment);
    }

    @Override
    public void onItemClick(Watchlist item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_KEY_WATCHLIST, item);
        Navigation.findNavController(getView()).navigate(R.id.watchlistFragment, bundle);
    }

    @Override
    public void onRemove(Watchlist item) {
        getViewModel().deleteWatchlist(item);
    }
}
