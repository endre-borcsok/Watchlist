package com.ebsoft.watchlist.ui.watchlist;

import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.di.WatchlistActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;
import com.ebsoft.watchlist.utils.Constants;

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
        return new CardViewAdapter<>(R.layout.layout_stock_item,
                WatchlistViewHolder.class);
    }

    @Provides
    Watchlist provideWatchlist(WatchlistActivity activity) {
        return (Watchlist) activity.getIntent().getSerializableExtra(Constants.EXTRA_KEY_WATCHLIST);
    }
}
