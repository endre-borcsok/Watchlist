package com.ebsoft.watchlist.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ebsoft.watchlist.data.model.db.Stock;

import java.util.List;

/**
 * Created by endre on 23/09/18.
 */

@Dao
public interface StockDao {

    @Query("SELECT * FROM stock")
    List<Stock> loadAll();

    @Delete
    void delete(Stock stock);

    @Query("SELECT * FROM stock WHERE listid LIKE :listid")
    List<Stock> findByWatchlist(String listid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Stock stock);
}
