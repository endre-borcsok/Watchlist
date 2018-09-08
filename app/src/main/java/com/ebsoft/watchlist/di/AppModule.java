package com.ebsoft.watchlist.di;

import android.content.Context;

import com.ebsoft.watchlist.ImageGrabberApplication;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.DataManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 07/09/18.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(ImageGrabberApplication application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }
}
