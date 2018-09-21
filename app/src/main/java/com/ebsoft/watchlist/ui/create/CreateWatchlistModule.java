package com.ebsoft.watchlist.ui.create;

import android.arch.lifecycle.ViewModelProvider;

import com.ebsoft.watchlist.ViewModelProviderFactory;
import com.ebsoft.watchlist.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 21/09/18.
 */

@Module
public class CreateWatchlistModule {

    @Provides
    ViewModelProvider.Factory createWatchlistViewModelProvider(
            CreateWatchlistViewModel createWatchlistViewModel) {
        return new ViewModelProviderFactory<>(createWatchlistViewModel);
    }

    @Provides
    CreateWatchlistViewModel provideCreateWatchListViewModel(DataManager DataManager) {
        return new CreateWatchlistViewModel(DataManager);
    }
}
