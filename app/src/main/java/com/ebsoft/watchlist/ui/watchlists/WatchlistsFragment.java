package com.ebsoft.watchlist.ui.watchlists;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.FragmentWatchlistsBinding;
import com.ebsoft.watchlist.di.MainActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.adapter.CardViewItemRemoveListener;
import com.ebsoft.watchlist.ui.base.BaseFragment;
import com.ebsoft.watchlist.ui.create.CreateWatchlistActivity;
import com.ebsoft.watchlist.ui.watchlist.WatchlistActivity;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

public class WatchlistsFragment extends BaseFragment <FragmentWatchlistsBinding, WatchlistsViewModel>
        implements WatchlistsNavigator,
        CardViewItemClickListener<Watchlist>,
        CardViewItemRemoveListener<Watchlist> {

    @Inject
    WatchlistsViewModel mWatchlistsViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Inject
    @MainActivityQualifier
    RecyclerView.LayoutManager mLayoutManager;

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
        viewDataBinding.mainActivityRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.mainActivityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.mainActivityRecyclerView.setAdapter(mAdapter);
        getViewModel().setNavigator(this);
        getViewModel().subscribeToLiveData(this);
        mAdapter.setItemClickListener(this);
        mAdapter.setItemRemoveListener(this);
    }

    @Override
    public void onActionButtonClick() {
        startActivity(new Intent(getContext(), CreateWatchlistActivity.class));
    }

    @Override
    public void onItemClick(Watchlist item) {
        Intent intent = new Intent(getContext(), WatchlistActivity.class);
        intent.putExtra(Constants.EXTRA_KEY_WATCHLIST, item);
        startActivity(intent);
    }

    @Override
    public void onRemove(Watchlist item) {
        getViewModel().deleteWatchlist(item);
    }
}
