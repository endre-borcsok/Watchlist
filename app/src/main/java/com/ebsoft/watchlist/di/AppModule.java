package com.ebsoft.watchlist.di;

import android.content.Context;

import com.ebsoft.watchlist.WatchlistApplication;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.DataManagerImpl;
import com.ebsoft.watchlist.network.APIManager;
import com.ebsoft.watchlist.network.APIManagerImpl;

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
    Context provideContext(WatchlistApplication application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }

    @Provides
    @Singleton
    APIManager provideAPIManager(APIManagerImpl apiManager) {
        return apiManager;
    }
}
