package com.ebsoft.watchlist.ui.main;

import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.di.MainActivityQualifier;

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
    LinearLayoutManager providesLayoutManager(MainActivity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    WatchlistAdapter providesAdapter() {
        return new WatchlistAdapter();
    }
}