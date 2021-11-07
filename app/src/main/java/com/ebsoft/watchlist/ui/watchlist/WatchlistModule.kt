package com.ebsoft.watchlist.ui.watchlist

import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.databinding.FragmentMainlistBinding
import com.ebsoft.watchlist.databinding.FragmentWatchlistBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.ui.create.CreateWatchlistFragment
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
        return FragmentWatchlistBinding.inflate(view.layoutInflater)
    }

    @Provides
    internal fun provideWatchListViewModel(DataManager: DataManager, watchlist: Watchlist): WatchlistViewModel {
        return WatchlistViewModel(DataManager, watchlist)
    }

    @Provides
    internal fun providesAdapter(): ListAdapter<Stock> {
        return ListAdapter(R.layout.layout_stock_item)
    }

    @Provides
    internal fun provideWatchlist(fragment: WatchlistFragment): Watchlist {
        return fragment.arguments!!.getSerializable(Constants.EXTRA_KEY_WATCHLIST) as Watchlist
    }
}
