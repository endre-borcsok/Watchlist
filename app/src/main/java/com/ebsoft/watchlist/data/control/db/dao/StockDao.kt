package com.ebsoft.watchlist.data.control.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import com.ebsoft.watchlist.data.model.db.Stock

/**
 * Created by endre on 23/09/18.
 */

@Dao
interface StockDao {

    @Query("SELECT * FROM stock")
    fun loadAll(): List<Stock>

    @Delete
    fun delete(stock: Stock)

    @Query("SELECT * FROM stock WHERE listid LIKE :listid")
    fun findByWatchlist(listid: Int): LiveData<List<Stock>>

    @Query("SELECT * FROM stock WHERE symbol LIKE :symbol")
    fun findBySymbol(symbol: String): List<Stock>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stock: Stock)

    @Update
    fun update(stock: Stock)
}
