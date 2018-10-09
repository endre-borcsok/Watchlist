package com.ebsoft.watchlist.data.control.db;

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
    public LiveData<List<Watchlist>> loadWatchlists() {
        return mDataBase.watchlistDao().loadAll();
    }

    @Override
    public LiveData<List<Stock>> loadStocks(Watchlist watchlist) {
        return mDataBase.stockDao().findByWatchlist(watchlist.getId());
    }

    @Override
    public Observable<Boolean> deleteWatchlist(Watchlist watchlist) {
        return Observable.fromCallable(() -> {
            mDataBase.watchlistDao().delete(watchlist);
            return true;
        });
    }

    @Override
    public Observable<List<Stock>> queryStock(String symbol) {
        return Observable.fromCallable(() -> mDataBase.stockDao().findBySymbol(symbol));
    }

    @Override
    public Observable<Boolean> deleteStock(Stock stock) {
        return Observable.fromCallable(() -> {
            mDataBase.stockDao().delete(stock);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveWatchlist(final Watchlist watchlist) {
        return Observable.fromCallable(() -> {
            mDataBase.watchlistDao().insert(watchlist);
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertStock(Stock stock) {
        return Observable.fromCallable(() -> {
            mDataBase.stockDao().insert(stock);
            return true;
        });
    }

    @Override
    public Observable<Boolean> updateStock(Stock stock) {
        return Observable.fromCallable(() -> {
            mDataBase.stockDao().update(stock);
            return true;
        });
    }
}
