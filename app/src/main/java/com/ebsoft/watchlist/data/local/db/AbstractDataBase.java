package com.ebsoft.watchlist.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ebsoft.watchlist.data.local.db.dao.SymbolDao;
import com.ebsoft.watchlist.data.local.db.dao.WatchlistDao;
import com.ebsoft.watchlist.data.model.db.Symbol;
import com.ebsoft.watchlist.data.model.db.Watchlist;

/**
 * Created by endre on 09/09/18.
 */

@Database(entities = {Watchlist.class, Symbol.class}, version = 2)
public abstract class AbstractDataBase extends RoomDatabase {

    public abstract WatchlistDao watchlistDao();

    public abstract SymbolDao symbolDao();
}