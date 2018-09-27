package com.ebsoft.watchlist.ui.watchlist;

import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;

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
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<Stock, ViewHolder>(R.layout.layout_recycler_view_element);
    }
}
