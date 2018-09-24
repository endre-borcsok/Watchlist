package com.ebsoft.watchlist.ui.watchlist;

import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 21/09/18.
 */

@Module
public class WatchlistModule {

    @Provides
    WatchlistViewModel provideWatchListViewModel(DataManager DataManager) {
        return new WatchlistViewModel(DataManager);
    }

    @Provides
    @WatchlistActivityQualifier
    LinearLayoutManager providesLayoutManager(WatchlistActivity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    StockAdapter providesAdapter() {
        return new StockAdapter();
    }
}
