package com.ebsoft.watchlist.data.local.db;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by endre on 09/09/18.
 */

public interface DBManager {

    Observable<List<Watchlist>> loadyWatchlists();

    Observable<Boolean> saveWatchlist(Watchlist watchlist);

    Observable<List<Stock>> loadStocks(Watchlist watchlist);

    Observable<List<Stock>> queryStock(String symbol);

    Observable<Boolean> saveStock(Stock stock);
}
