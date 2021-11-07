package com.ebsoft.watchlist.data.control.db

import android.arch.lifecycle.LiveData
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by endre on 09/09/18.
 */

@Singleton
class DBManagerImpl @Inject
constructor(private val mDataBase: AbstractDataBase) : DBManager {

    override fun loadWatchlists(): LiveData<List<Watchlist>> {
        return mDataBase.watchlistDao().loadAll()
    }

    override fun loadStocks(watchlist: Watchlist): LiveData<List<Stock>> {
        return mDataBase.stockDao().findByWatchlist(watchlist.id)
    }

    override fun deleteWatchlist(watchlist: Watchlist): Observable<Boolean> {
        return Observable.fromCallable {
            mDataBase.watchlistDao().delete(watchlist)
            true
        }
    }

    override fun queryStock(symbol: String): Observable<List<Stock>> {
        return Observable.fromCallable { mDataBase.stockDao().findBySymbol(symbol) }
    }

    override fun deleteStock(stock: Stock): Observable<Boolean> {
        return Observable.fromCallable {
            mDataBase.stockDao().delete(stock)
            true
        }
    }

    override fun saveWatchlist(watchlist: Watchlist): Observable<Boolean> {
        return Observable.fromCallable {
            mDataBase.watchlistDao().insert(watchlist)
            true
        }
    }

    override fun insertStock(stock: Stock): Observable<Boolean> {
        return Observable.fromCallable {
            mDataBase.stockDao().insert(stock)
            true
        }
    }

    override fun updateStock(stock: Stock): Observable<Boolean> {
        return Observable.fromCallable {
            mDataBase.stockDao().update(stock)
            true
        }
    }
}
