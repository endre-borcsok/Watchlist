package com.ebsoft.watchlist.data.control.network

import com.ebsoft.watchlist.data.control.network.IEX.IEXApi
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.yahoo.Item
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by endre on 09/09/18.
 */

@Singleton
class APIManagerImpl @Inject
constructor(private val mYahooApi: YahooAPI, private val mIEXApi: IEXApi) : APIManager {

    override suspend fun searchSymbol(symbol: String): List<Item> {
        val response = mYahooApi.searchSymbol(symbol).await()
        return if (response.isSuccessful) {
            response.body()?.symbolSearchResponse?.items!!
        } else {
            ArrayList()
        }
    }

    override suspend fun getBatchQuote(stocks: List<Stock>) {
        val response = mIEXApi.getQuote(getSymbols(stocks)).await()
        if (response.isSuccessful) {
            val quoteMap = response.body().orEmpty()
            for (stock in stocks) {
                val stockQuote = quoteMap[stock.symbol]
                if (stockQuote != null) {
                    stock.update(stockQuote.quote)
                }
            }
        }
    }

    private fun getSymbols(stocks: List<Stock>): String {
        val sb = StringBuilder()
        for ((_, symbol) in stocks) {
            sb.append(',')
            sb.append(symbol)
        }
        if (sb.isNotEmpty()) sb.deleteCharAt(0)
        return sb.toString()
    }
}
