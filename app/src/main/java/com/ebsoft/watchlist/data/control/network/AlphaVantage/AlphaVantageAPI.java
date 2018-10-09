package com.ebsoft.watchlist.data.control.network.AlphaVantage;

import com.ebsoft.watchlist.data.model.AlphaVantage.AVQuote;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlphaVantageAPI {

    @GET("query?function=GLOBAL_QUOTE&apikey=JU9JG5S6ZVGCH2DM")
    Observable<Response<AVQuote>> getQuote(@Query("symbol") String symbol);
}
