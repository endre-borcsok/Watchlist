package com.ebsoft.watchlist.data.control.nonpersistent;

import android.arch.lifecycle.MutableLiveData;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VolatileStorageImpl implements VolatileStorage {

    private final MutableLiveData<List<Watchlist>> mWatchlist;

    private final MutableLiveData<Map<String, List<Stock>>> mStockMap;

    @Inject
    public VolatileStorageImpl() {
        mWatchlist = new MutableLiveData<>();
        mStockMap = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<List<Watchlist>> getWatchlistLiveData() {
        return mWatchlist;
    }

    @Override
    public MutableLiveData<Map<String, List<Stock>>> getStockMapLiveData() {
        return mStockMap;
    }
}
