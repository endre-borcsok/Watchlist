package com.ebsoft.watchlist.data.local.db;

import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;
import java.util.concurrent.Callable;

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
    public Observable<List<Watchlist>> loadWatchlists() {
        return Observable.fromCallable(() -> mDataBase.watchlistDao().loadAll());
    }

    @Override
    public Observable<Boolean> insertWatchlist(final Watchlist watchlist) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDataBase.watchlistDao().insert(watchlist);
                return true;
            }
        });
    }
}
