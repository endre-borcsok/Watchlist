package com.ebsoft.watchlist.data.control.network

import android.util.Log

import com.ebsoft.watchlist.data.control.network.IEX.IEXApi
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.yahoo.Item

import java.util.ArrayList

import javax.inject.Inject
import javax.inject.Singleton

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by endre on 09/09/18.
 */

@Singleton
class APIManagerImpl @Inject
constructor(private val mYahooApi: YahooAPI, private val mIEXApi: IEXApi) : APIManager {

    private val TAG = APIManagerImpl::class.java.simpleName

    override suspend fun searchSymbol(symbol: String): List<Item> {
        val response = mYahooApi.searchSymbol(symbol).await()
        return if (response.isSuccessful) {
            response.body()?.symbolSearchResponse?.items!!
        } else {
            ArrayList()
        }
    }

    override fun getBatchQuote(stocks: List<Stock>, listener: QuoteQueryListener): Disposable {
        return mIEXApi.getQuote(getSymbols(stocks))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mapResponse ->
                    val quoteMap = mapResponse.body()
                    for (stock in stocks) {
                        val stockQuote = quoteMap!![stock.symbol]
                        if (stockQuote != null) {
                            stock.update(stockQuote.quote)
                        }
                    }
                    listener.onComplete()
                }, { throwable ->
                    Log.e(TAG, throwable.localizedMessage)
                    listener.onComplete()
                })
    }

    private fun getSymbols(stocks: List<Stock>): String {
        val sb = StringBuilder()
        for ((_, symbol) in stocks) {
            sb.append(',')
            sb.append(symbol)
        }
        if (sb.length > 0) sb.deleteCharAt(0)
        return sb.toString()
    }
}
