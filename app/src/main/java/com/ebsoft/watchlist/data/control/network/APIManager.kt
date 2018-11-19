package com.ebsoft.watchlist.data.control.network

import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.yahoo.Item
import com.ebsoft.watchlist.data.model.yahoo.SymbolSearch

import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * Created by endre on 09/09/18.
 */

interface APIManager {

    suspend fun searchSymbol(symbol: String): List<Item>

    fun getBatchQuote(stocks: List<Stock>, listener: QuoteQueryListener): Disposable
}
