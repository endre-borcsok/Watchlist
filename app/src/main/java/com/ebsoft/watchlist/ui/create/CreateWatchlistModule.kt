package com.ebsoft.watchlist.ui.create

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.databinding.FragmentCreateWatchlistBinding

import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Created by endre on 21/09/18.
 */

@Module
class CreateWatchlistModule {
    @Provides
    @Reusable
    internal fun providesBinding(view: CreateWatchlistFragment): FragmentCreateWatchlistBinding {
        return FragmentCreateWatchlistBinding.inflate(view.layoutInflater)
    }

    @Provides
    @Reusable
    internal fun provideCreateWatchListViewModel(DataManager: DataManager): CreateWatchlistViewModel {
        return CreateWatchlistViewModel(DataManager)
    }
}
