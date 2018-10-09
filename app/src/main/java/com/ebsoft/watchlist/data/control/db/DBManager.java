package com.ebsoft.watchlist.data.control.db;

import android.arch.lifecycle.LiveData;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by endre on 09/09/18.
 */

public interface DBManager {

    Observable<LiveData<List<Watchlist>>> loadWatchlists();

    Observable<Boolean> deleteWatchlist(Watchlist watchlist);

    Observable<Boolean> saveWatchlist(Watchlist watchlist);

    Observable<LiveData<List<Stock>>> loadStocks(Watchlist watchlist);

    Observable<List<Stock>> queryStock(String symbol);

    Observable<Boolean> deleteStock(Stock stock);

    Observable<Boolean> insertStock(Stock stock);

    Observable<Boolean> updateStock(Stock stock);
}
