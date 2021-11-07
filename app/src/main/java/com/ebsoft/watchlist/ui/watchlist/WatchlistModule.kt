package com.ebsoft.watchlist.ui.watchlist

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.databinding.FragmentWatchlistBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.utils.Constants
import dagger.Module
import dagger.Provides

/**
 * Created by endre on 21/09/18.
 */

@Module
class WatchlistModule {
    @Provides
    internal fun providesBinding(view: WatchlistFragment): FragmentWatchlistBinding {
        val viewDataBinding = FragmentWatchlistBinding.inflate(view.layoutInflater)
        viewDataBinding.swipeRefresh.setOnRefreshListener(view)
        return viewDataBinding
    }

    @Provides
    internal fun provideWatchListViewModel(DataManager: DataManager, watchlist: Watchlist): WatchlistViewModel {
        return WatchlistViewModel(DataManager, watchlist)
    }

    @Provides
    internal fun providesAdapter(viewDataBinding: FragmentWatchlistBinding, fragment: WatchlistFragment): ListAdapter<Stock> {
        val adapter = ListAdapter<Stock>(R.layout.layout_stock_item)
        adapter.setItemClickListener(fragment)
        adapter.setItemRemoveListener(fragment)
        viewDataBinding.watchlistRecyclerView.layoutManager = LinearLayoutManager(fragment.context)
        viewDataBinding.watchlistRecyclerView.itemAnimator = DefaultItemAnimator()
        viewDataBinding.watchlistRecyclerView.adapter = adapter
        return adapter
    }

    @Provides
    internal fun provideWatchlist(fragment: WatchlistFragment): Watchlist {
        return fragment.arguments!!.getSerializable(Constants.EXTRA_KEY_WATCHLIST) as Watchlist
    }
}
