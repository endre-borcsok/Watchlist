package com.ebsoft.watchlist.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.di.MainActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 07/09/18.
 */

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager DataManager) {
        return new MainViewModel(DataManager);
    }

    @Provides
    @MainActivityQualifier
    RecyclerView.LayoutManager providesLayoutManager(MainActivity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<Watchlist>(R.layout.layout_main_menu_cardview_item);
    }
}