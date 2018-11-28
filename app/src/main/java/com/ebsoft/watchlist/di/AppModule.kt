package com.ebsoft.watchlist.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.DataManagerImpl
import com.ebsoft.watchlist.data.control.db.AbstractDataBase
import com.ebsoft.watchlist.data.control.db.DBManager
import com.ebsoft.watchlist.data.control.db.DBManagerImpl
import com.ebsoft.watchlist.data.control.network.APIManager
import com.ebsoft.watchlist.data.control.network.APIManagerImpl
import com.ebsoft.watchlist.data.control.network.IEX.IEXApi
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI
import com.ebsoft.watchlist.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by endre on 07/09/18.
 */

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(dataManager: DataManagerImpl): DataManager {
        return dataManager
    }

    @Provides
    @Singleton
    internal fun provideApiManager(apiManager: APIManagerImpl): APIManager {
        return apiManager
    }

    @Provides
    @Singleton
    internal fun provideDbManager(dbManager: DBManagerImpl): DBManager {
        return dbManager
    }

    @Provides
    internal fun provideDatabase(context: Context): AbstractDataBase {
        return Room.databaseBuilder(context, AbstractDataBase::class.java, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    internal fun provideYahooApi(gson: Gson): YahooAPI {
        return Retrofit.Builder()
                .baseUrl(Constants.YAHOO_API_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(YahooAPI::class.java)
    }

    @Provides
    internal fun provideIEXApi(gson: Gson): IEXApi {
        return Retrofit.Builder()
                .baseUrl(Constants.IEX_API_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(IEXApi::class.java)
    }

    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
                .setLenient()
                .create()
    }
}
