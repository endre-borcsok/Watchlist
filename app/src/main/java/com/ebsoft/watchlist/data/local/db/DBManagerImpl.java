package com.ebsoft.watchlist.data.local.db;

import android.arch.lifecycle.LiveData;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by endre on 09/09/18.
 */

@Singleton
public class DBManagerImpl implements DBManager {

    private AbstractDataBase mDataBase;

    @Inject
    public DBManagerImpl(AbstractDataBase dataBase) {
        this.mDataBase = dataBase;
    }


    @Override
    public Observable<List<Watchlist>> loadyWatchlists() {
        return Observable.fromCallable(() -> mDataBase.watchlistDao().loadAll());
    }

    @Override
    public Observable<LiveData<List<Stock>>> loadStocks(Watchlist watchlist) {
        return Observable.fromCallable(() -> mDataBase.symbolDao()
                .findByWatchlist(watchlist.getName()));
    }

    @Override
    public Observable<List<Stock>> queryStock(String symbol) {
        return Observable.fromCallable(() -> mDataBase.symbolDao().findBySymbol(symbol));
    }

    @Override
    public Observable<Boolean> saveWatchlist(final Watchlist watchlist) {
        return Observable.fromCallable(() -> {
            mDataBase.watchlistDao().insert(watchlist);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveStock(Stock stock) {
        return Observable.fromCallable(() -> {
            mDataBase.symbolDao().insert(stock);
            return true;
        });
    }
}
