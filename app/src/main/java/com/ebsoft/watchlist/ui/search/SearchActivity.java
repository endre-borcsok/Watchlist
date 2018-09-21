package com.ebsoft.watchlist.ui.search;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivitySearchBinding;
import com.ebsoft.watchlist.di.SearchActivityQualifier;
import com.ebsoft.watchlist.ui.base.BaseActivity;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> implements
        android.support.v7.widget.SearchView.OnQueryTextListener, SearchListener {

    @Inject
    SearchViewModel mSearchViewModel;

    @Inject
    SearchAdapter mAdapter;

    @Inject
    @SearchActivityQualifier
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        return mSearchViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ActivitySearchBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.searchRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.searchRecyclerView.setAdapter(mAdapter);
        viewDataBinding.searchView.setOnQueryTextListener(this);
        mAdapter.setSearchListener(this);
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
    public void onSearchSelected(String result) {
        Log.d("D", result);
    }
}
