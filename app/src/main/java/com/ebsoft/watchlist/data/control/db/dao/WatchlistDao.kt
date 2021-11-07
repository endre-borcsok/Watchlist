package com.ebsoft.watchlist.data.control.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ebsoft.watchlist.data.model.db.Watchlist

/**
 * Created by endre on 09/09/18.
 */

@Dao
interface WatchlistDao {

    @Query("SELECT * FROM watchlist")
    fun loadAll(): LiveData<List<Watchlist>>

    @Delete
    fun delete(watchlist: Watchlist)

    @Query("SELECT * FROM watchlist WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): Watchlist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(watchlist: Watchlist)
}
