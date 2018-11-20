package com.ebsoft.watchlist.ui.watchlist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.databinding.FragmentWatchlistBinding;
import com.ebsoft.watchlist.ui.adapter.ListAdapter;
import com.ebsoft.watchlist.ui.adapter.ListItemClickListener;
import com.ebsoft.watchlist.ui.adapter.ListItemRemoveListener;
import com.ebsoft.watchlist.ui.base.BaseFragment;
import com.ebsoft.watchlist.ui.dialog.DeleteDialog;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class WatchlistFragment extends BaseFragment<FragmentWatchlistBinding, WatchlistViewModel>
        implements WatchlistNavigator, DeleteDialog.DeleteDialogListener,
        SwipeRefreshLayout.OnRefreshListener, ListItemClickListener<Stock>, ListItemRemoveListener<Stock> {

    @Inject
    WatchlistViewModel mWatchlistViewModel;

    @Inject
    ListAdapter mAdapter;

    @Override
    public void setup() {
        mAdapter.setItemClickListener(this);
        mAdapter.setItemRemoveListener(this);
        FragmentWatchlistBinding viewDataBinding = getViewDataBinding();
        viewDataBinding.watchlistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.watchlistRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.watchlistRecyclerView.setAdapter(mAdapter);
        viewDataBinding.swipeRefresh.setOnRefreshListener(this);
        getViewModel().setNavigator(this);
        getViewModel().subscribeToLiveData(this);
        getActivity().setTitle(getViewModel().getWatchlist().getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_watchlist;
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
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.EXTRA_KEY_WATCHLIST, getViewModel().getWatchlist());
        Navigation.findNavController(getView()).navigate(R.id.searchFragment, bundle);
    }

    @Override
    public void onItemClick(Stock item) {
        // TODO
    }

    @Override
    public void onRemove(Stock item) {
        DialogFragment dialog = new DeleteDialog();
        ((DeleteDialog) dialog).setListener(this);
        Bundle args = new Bundle();
        args.putSerializable(Constants.DELETE_DIALOG_SERIALIZABLE_KEY, item);
        dialog.setArguments(args);
        dialog.show(getActivity().getSupportFragmentManager(), this.getClass().getSimpleName());
    }

    @Override
    public void onRefresh() {
        getViewModel().refresh();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        getViewModel().removeStock((Stock) dialog.getArguments()
                .getSerializable(Constants.DELETE_DIALOG_SERIALIZABLE_KEY));
    }
}
