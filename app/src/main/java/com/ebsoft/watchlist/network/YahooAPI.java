package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.YahooSymbolLookup;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by endre on 09/09/18.
 */

public interface YahooAPI {

    @GET("autoc?format=json&region=1&lang=en")
    Observable<Response<YahooSymbolLookup>> searchSymbol(@Query("query") String symbol);
}