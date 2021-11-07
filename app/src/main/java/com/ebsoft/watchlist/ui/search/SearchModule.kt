package com.ebsoft.watchlist.ui.search

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.yahoo.Item
import com.ebsoft.watchlist.databinding.FragmentSearchBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Created by endre on 09/09/18.
 */

@Module
class SearchModule {
    @Provides
    @Reusable
    internal fun providesBinding(view: SearchFragment): FragmentSearchBinding {
        val viewDataBinding = FragmentSearchBinding.inflate(view.layoutInflater)
        viewDataBinding.searchView.setOnQueryTextListener(view)
        return viewDataBinding
    }

    @Provides
    @Reusable
    internal fun provideSearchViewModel(DataManager: DataManager): SearchViewModel {
        return SearchViewModel(DataManager)
    }

    @Provides
    internal fun providesAdapter(viewDataBinding: FragmentSearchBinding, fragment: SearchFragment): ListAdapter<Item> {
        val adapter = ListAdapter<Item>(R.layout.layout_search_item)
        viewDataBinding.searchRecyclerView.layoutManager = LinearLayoutManager(fragment.context)
        viewDataBinding.searchRecyclerView.itemAnimator = DefaultItemAnimator()
        viewDataBinding.searchRecyclerView.adapter = adapter
        adapter.setItemClickListener(fragment)
        return adapter
    }
}
