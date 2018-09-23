package com.ebsoft.watchlist.data.local.db;

import com.ebsoft.watchlist.data.model.db.Symbol;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by endre on 09/09/18.
 */

public interface DBManager {

    Observable<List<Watchlist>> loadWatchlists();

    Observable<Boolean> insertWatchlist(Watchlist watchlist);

    Observable<List<Symbol>> loadSymbolsForWatchlist(Watchlist watchlist);

    Observable<Boolean> insertSymbol(Symbol symbol);
}
