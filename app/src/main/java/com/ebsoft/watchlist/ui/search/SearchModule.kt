package com.ebsoft.watchlist.ui.search

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.yahoo.Item
import com.ebsoft.watchlist.databinding.FragmentMainlistBinding
import com.ebsoft.watchlist.databinding.FragmentSearchBinding
import com.ebsoft.watchlist.databinding.FragmentSearchBindingImpl
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.ui.create.CreateWatchlistFragment

import dagger.Module
import dagger.Provides

/**
 * Created by endre on 09/09/18.
 */

@Module
class SearchModule {
    @Provides
    internal fun providesBinding(view: SearchFragment): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(view.layoutInflater)
    }

    @Provides
    internal fun provideSearchViewModel(DataManager: DataManager): SearchViewModel {
        return SearchViewModel(DataManager)
    }

    @Provides
    internal fun providesAdapter(viewDataBinding: FragmentSearchBinding, fragment: SearchFragment): ListAdapter<Item> {
        val adapter = ListAdapter<Item>(R.layout.layout_search_item)
        viewDataBinding.searchRecyclerView.layoutManager = LinearLayoutManager(fragment.context)
        viewDataBinding.searchRecyclerView.itemAnimator = DefaultItemAnimator()
        viewDataBinding.searchRecyclerView.adapter = adapter
        return adapter
    }
}
