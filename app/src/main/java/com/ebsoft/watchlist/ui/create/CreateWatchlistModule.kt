package com.ebsoft.watchlist.ui.create

import com.ebsoft.watchlist.data.DataManager

import dagger.Module
import dagger.Provides

/**
 * Created by endre on 21/09/18.
 */

@Module
class CreateWatchlistModule {

    @Provides
    internal fun provideCreateWatchListViewModel(DataManager: DataManager): CreateWatchlistViewModel {
        return CreateWatchlistViewModel(DataManager)
    }
}
