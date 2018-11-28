package com.ebsoft.watchlist.data.control.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.ebsoft.watchlist.data.control.db.dao.StockDao
import com.ebsoft.watchlist.data.control.db.dao.WatchlistDao
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist

/**
 * Created by endre on 09/09/18.
 */

@Database(entities = [Watchlist::class, Stock::class], version = 2, exportSchema = false)
abstract class AbstractDataBase : RoomDatabase() {

    abstract fun watchlistDao(): WatchlistDao

    abstract fun stockDao(): StockDao
}