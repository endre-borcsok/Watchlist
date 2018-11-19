package com.ebsoft.watchlist.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.DataManagerImpl;
import com.ebsoft.watchlist.data.control.db.AbstractDataBase;
import com.ebsoft.watchlist.data.control.db.DBManager;
import com.ebsoft.watchlist.data.control.db.DBManagerImpl;
import com.ebsoft.watchlist.data.control.network.APIManager;
import com.ebsoft.watchlist.data.control.network.APIManagerImpl;
import com.ebsoft.watchlist.data.control.network.IEX.IEXApi;
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI;
import com.ebsoft.watchlist.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    APIManager provideApiManager(APIManagerImpl apiManager) { return apiManager; }

    @Provides
    @Singleton
    DBManager provideDbManager(DBManagerImpl dbManager) { return dbManager; }

    @Provides
    AbstractDataBase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AbstractDataBase.class, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    YahooAPI provideYahooApi(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.YAHOO_API_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
                .build()
                .create(YahooAPI.class);
    }

    @Provides
    IEXApi provideIEXApi(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Constants.IEX_API_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(IEXApi.class);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
