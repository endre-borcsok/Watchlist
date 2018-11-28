package com.ebsoft.watchlist.data.control.db

import android.arch.lifecycle.LiveData

import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist

import io.reactivex.Observable

/**
 * Created by endre on 09/09/18.
 */

interface DBManager {

    fun loadWatchlists(): LiveData<List<Watchlist>>

    fun loadStocks(watchlist: Watchlist): LiveData<List<Stock>>

    fun deleteWatchlist(watchlist: Watchlist): Observable<Boolean>

    fun saveWatchlist(watchlist: Watchlist): Observable<Boolean>

    fun queryStock(symbol: String): Observable<List<Stock>>

    fun deleteStock(stock: Stock): Observable<Boolean>

    fun insertStock(stock: Stock): Observable<Boolean>

    fun updateStock(stock: Stock): Observable<Boolean>
}
