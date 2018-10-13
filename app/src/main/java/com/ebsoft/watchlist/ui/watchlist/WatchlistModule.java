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
    RecyclerView.LayoutManager providesLayoutManager(WatchlistFragment fragment) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<Stock>(R.layout.layout_stock_item);
    }

    @Provides
    Watchlist provideWatchlist(WatchlistFragment fragment) {
        return (Watchlist) fragment.getArguments().getSerializable(Constants.EXTRA_KEY_WATCHLIST);
    }
}
