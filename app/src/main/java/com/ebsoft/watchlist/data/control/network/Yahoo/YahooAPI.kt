package com.ebsoft.watchlist.data.control.network.Yahoo

import com.ebsoft.watchlist.data.model.yahoo.SymbolSearch

import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by endre on 09/09/18.
 */

interface YahooAPI {

    @GET("autoc?format=json&region=1&lang=en")
    fun searchSymbol(@Query("query") symbol: String): Deferred<Response<SymbolSearch>>
}