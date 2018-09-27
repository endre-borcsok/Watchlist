package com.ebsoft.watchlist.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.databinding.ActivitySearchBinding;
import com.ebsoft.watchlist.di.SearchActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.ui.adapter.CardViewItemClickListener;
import com.ebsoft.watchlist.ui.base.BaseActivity;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Inject;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> implements
        android.support.v7.widget.SearchView.OnQueryTextListener, CardViewItemClickListener<String> {

    @Inject
    SearchViewModel mSearchViewModel;

    @Inject
    CardViewAdapter mAdapter;

    @Inject
    @SearchActivityQualifier
    LinearLayoutManager mLayoutManager;

    @Override
    public void setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ActivitySearchBinding viewDataBinding = getViewDatabinding();
        viewDataBinding.searchRecyclerView.setLayoutManager(mLayoutManager);
        viewDataBinding.searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.searchRecyclerView.setAdapter(mAdapter);
        viewDataBinding.searchView.setOnQueryTextListener(this);
        mAdapter.setItemClickListener(this);
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
    public void onItemClick(String item) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.SEARCH_RESULT_KEY, item);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
