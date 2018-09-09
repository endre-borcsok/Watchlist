package com.ebsoft.watchlist.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.DataManagerImpl;
import com.ebsoft.watchlist.data.local.db.AbstractDataBase;
import com.ebsoft.watchlist.data.local.db.DBManager;
import com.ebsoft.watchlist.data.local.db.DBManagerImpl;
import com.ebsoft.watchlist.network.APIManager;
import com.ebsoft.watchlist.network.APIManagerImpl;
import com.ebsoft.watchlist.utils.Constants;

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
    Context provideContext(Application application) {
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

    @Provides
    @Singleton
    DBManager provideDataBase(DBManagerImpl dbManager) {
        return dbManager;
    }

    @Provides
    @Singleton
    AbstractDataBase provideAbstractDatabase(Context context) {
        return Room.databaseBuilder(context, AbstractDataBase.class, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
