package com.ebsoft.watchlist.ui.watchlist;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.ListAdapter;
import com.ebsoft.watchlist.utils.Constants;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 21/09/18.
 */

@Module
public class WatchlistModule {

    @Provides
    WatchlistViewModel provideWatchListViewModel(DataManager DataManager, Watchlist watchlist) {
        return new WatchlistViewModel(DataManager, watchlist);
    }

    @Provides
    ListAdapter providesAdapter() {
        return new ListAdapter<Stock>(R.layout.layout_stock_item);
    }

    @Provides
    Watchlist provideWatchlist(WatchlistFragment fragment) {
        return (Watchlist) fragment.getArguments().getSerializable(Constants.EXTRA_KEY_WATCHLIST);
    }
}
