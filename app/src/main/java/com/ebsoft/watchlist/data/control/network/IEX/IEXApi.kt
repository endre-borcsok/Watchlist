package com.ebsoft.watchlist.data.control.network.IEX

import com.ebsoft.watchlist.data.model.IEX.StockQuote
import com.ebsoft.watchlist.data.model.IEX.Symbol
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IEXApi {

    @GET("/stable/stock/{symbols}/batch?types=quote")
    fun getQuote(@Path("symbols") symbols: String): Deferred<Response<StockQuote>>

    @GET("/stable/search/{symbol}")
    fun searchSymbol(@Path("symbol") symbol: String): Deferred<Response<List<Symbol>>>
}
