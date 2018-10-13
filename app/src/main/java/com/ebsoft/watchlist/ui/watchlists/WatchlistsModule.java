package com.ebsoft.watchlist.ui.watchlists;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class WatchlistsModule {

    @Provides
    WatchlistsViewModel provideMainViewModel(DataManager DataManager) {
        return new WatchlistsViewModel(DataManager);
    }

    @Provides
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<Watchlist>(R.layout.layout_main_menu_cardview_item);
    }
}
