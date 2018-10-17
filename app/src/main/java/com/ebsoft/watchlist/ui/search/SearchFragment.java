package com.ebsoft.watchlist.ui.search;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.data.model.yahoo.Item;
import com.ebsoft.watchlist.databinding.FragmentSearchBinding;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.base.BaseFragment;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> implements
        android.support.v7.widget.SearchView.OnQueryTextListener, CardViewItemClickListener<Item> {

    @Inject
    SearchViewModel mSearchViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Override
    public void setup() {
        getActivity().setTitle(R.string.search_activity_label);
        FragmentSearchBinding viewDataBinding = getViewDataBinding();
        viewDataBinding.searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.searchRecyclerView.setAdapter(mAdapter);
        viewDataBinding.searchView.setOnQueryTextListener(this);
        mAdapter.setItemClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        return mSearchViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        getViewModel().performSearch(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onItemClick(Item item) {
        Watchlist wlist = (Watchlist) getArguments().getSerializable(Constants.EXTRA_KEY_WATCHLIST);
        getViewModel().insertStock(Stock.Companion.create(item, wlist));
        Navigation.findNavController(getView()).navigateUp();
    }
}
