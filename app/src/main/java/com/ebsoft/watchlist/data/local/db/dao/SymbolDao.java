package com.ebsoft.watchlist.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ebsoft.watchlist.data.model.db.Symbol;

import java.util.List;

/**
 * Created by endre on 23/09/18.
 */

@Dao
public interface SymbolDao {

    @Query("SELECT * FROM symbol")
    List<Symbol> loadAll();

    @Delete
    void delete(Symbol symbol);

    @Query("SELECT * FROM symbol WHERE listid LIKE :listid")
    List<Symbol> findByWatchlist(String listid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Symbol symbol);
}
