package com.ebsoft.watchlist.data.control.network.IEX

import com.ebsoft.watchlist.data.model.IEX.StockQuote

import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IEXApi {

    @GET("1.0/stock/market/batch?&types=quote")
    fun getQuote(@Query("symbols") symbols: String): Deferred<Response<Map<String, StockQuote>>>
}
