package com.ebsoft.watchlist.data.control.db.dao;

import android.arch.lifecycle.LiveData;
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
    LiveData<List<Watchlist>> loadAll();

    @Delete
    void delete(Watchlist watchlist);

    @Query("SELECT * FROM watchlist WHERE id LIKE :id LIMIT 1")
    Watchlist findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Watchlist watchlist);
}
