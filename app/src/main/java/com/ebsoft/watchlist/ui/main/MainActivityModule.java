package com.ebsoft.watchlist.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.ebsoft.watchlist.ViewModelProviderFactory;
import com.ebsoft.watchlist.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 07/09/18.
 */


@Module
public class MainActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager) {
        return new MainViewModel(dataManager);
    }
}