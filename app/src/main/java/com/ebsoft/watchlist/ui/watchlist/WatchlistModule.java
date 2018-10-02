package com.ebsoft.watchlist.ui.watchlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
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
    WatchlistViewModel provideWatchListViewModel(DataManager DataManager, Watchlist watchlist) {
        return new WatchlistViewModel(DataManager, watchlist);
    }

    @Provides
    @WatchlistActivityQualifier
    RecyclerView.LayoutManager providesLayoutManager(WatchlistActivity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<Stock>(R.layout.layout_stock_item);
    }

    @Provides
    Watchlist provideWatchlist(WatchlistActivity activity) {
        return (Watchlist) activity.getIntent().getSerializableExtra(Constants.EXTRA_KEY_WATCHLIST);
    }
}
