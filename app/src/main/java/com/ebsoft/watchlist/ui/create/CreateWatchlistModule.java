package com.ebsoft.watchlist.ui.create;

import com.ebsoft.watchlist.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 21/09/18.
 */

@Module
public class CreateWatchlistModule {

    @Provides
    CreateWatchlistViewModel provideCreateWatchListViewModel(DataManager DataManager) {
        return new CreateWatchlistViewModel(DataManager);
    }
}
