package com.ebsoft.watchlist.ui.watchlists;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.di.MainActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class WatchlistsFragmentModule {

    @Provides
    WatchlistsViewModel provideMainViewModel(DataManager DataManager) {
        return new WatchlistsViewModel(DataManager);
    }

    @Provides
    @MainActivityQualifier
    RecyclerView.LayoutManager providesLayoutManager(WatchlistsFragment fragment) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<Watchlist>(R.layout.layout_main_menu_cardview_item);
    }
}
