package com.ebsoft.watchlist.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by endre on 09/09/18.
 */

public class YahooApiFactory {

    private static String END_POINT = "http://d.yimg.com/autoc.finance.yahoo.com/";

    public static YahooAPI createYahooAPI() {
        return new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(YahooAPI.class);
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
