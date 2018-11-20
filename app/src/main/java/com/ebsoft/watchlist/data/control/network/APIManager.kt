package com.ebsoft.watchlist.data.control.network

import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.yahoo.Item

/**
 * Created by endre on 09/09/18.
 */

interface APIManager {

    suspend fun searchSymbol(symbol: String): List<Item>

    suspend fun getBatchQuote(stocks: List<Stock>)
}
