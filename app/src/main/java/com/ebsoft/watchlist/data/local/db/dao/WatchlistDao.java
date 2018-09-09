package com.ebsoft.watchlist.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;

/**
 * Created by endre on 09/09/18.
 */

@Dao
public interface WatchlistDao {

    @Query("SELECT * FROM watchlist")
    List<Watchlist> loadAll();

    @Delete
    void delete(Watchlist watchlist);

    @Query("SELECT * FROM watchlist WHERE name LIKE :name LIMIT 1")
    Watchlist findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Watchlist watchlist);
}
