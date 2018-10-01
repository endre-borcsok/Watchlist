package com.ebsoft.watchlist.network.IEX;

import com.ebsoft.watchlist.data.model.IEX.Quote;
import com.ebsoft.watchlist.data.model.IEX.StockQuote;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IEXApi {

    @GET("1.0/stock/market/batch?&types=quote")
    Observable<Response<Map<String, StockQuote>>> getQuote(@Query("symbols") String symbols);
}
