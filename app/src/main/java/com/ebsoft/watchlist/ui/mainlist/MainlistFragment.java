package com.ebsoft.watchlist.ui.mainlist;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.databinding.FragmentMainlistBinding;
import com.ebsoft.watchlist.ui.adapter.ListAdapter;
import com.ebsoft.watchlist.ui.adapter.ListItemClickListener;
import com.ebsoft.watchlist.ui.adapter.ListItemRemoveListener;
import com.ebsoft.watchlist.ui.base.BaseFragment;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class MainlistFragment extends BaseFragment <FragmentMainlistBinding, MainlistViewModel>
        implements MainlistNavigator, ListItemClickListener<Watchlist>, ListItemRemoveListener<Watchlist> {

    @Inject
    MainlistViewModel mMainlistViewModel;

    @Inject
    ListAdapter mAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mainlist;
    }

    @Override
    public MainlistViewModel getViewModel() {
        return mMainlistViewModel;
    }

    @Override
    public void setup() {
        getActivity().setTitle(R.string.main_fragment_label);
        FragmentMainlistBinding viewDataBinding = getViewDataBinding();
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
