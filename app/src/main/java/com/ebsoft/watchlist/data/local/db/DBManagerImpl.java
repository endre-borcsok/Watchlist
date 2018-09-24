package com.ebsoft.watchlist.data.local.db;

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
    public Observable<List<Watchlist>> queryWatchlists() {
        return Observable.fromCallable(() -> mDataBase.watchlistDao().loadAll());
    }

    @Override
    public Observable<List<Stock>> queryWatchlist(Watchlist watchlist) {
        return Observable.fromCallable(() -> mDataBase.symbolDao().findByWatchlist(watchlist.name));
    }

    @Override
    public Observable<List<Stock>> querySymbol(String symbol) {
        return Observable.fromCallable(() -> mDataBase.symbolDao().findBySymbol(symbol));
    }

    @Override
    public Observable<Boolean> insertWatchlist(final Watchlist watchlist) {
        return Observable.fromCallable(() -> {
            mDataBase.watchlistDao().insert(watchlist);
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertStock(Stock stock) {
        return Observable.fromCallable(() -> {
            mDataBase.symbolDao().insert(stock);
            return true;
        });
    }
}
