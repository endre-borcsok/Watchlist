package com.ebsoft.imagegrabber.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.ebsoft.imagegrabber.ViewModelProviderFactory;

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
    MainViewModel provideMainViewModel() {
        return new MainViewModel();
    }
}