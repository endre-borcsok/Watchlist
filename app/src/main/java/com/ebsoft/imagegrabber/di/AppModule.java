package com.ebsoft.imagegrabber.di;

import android.content.Context;

import com.ebsoft.imagegrabber.ImageGrabberApplication;
import com.ebsoft.imagegrabber.data.DataManager;
import com.ebsoft.imagegrabber.data.DataManagerImpl;

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
