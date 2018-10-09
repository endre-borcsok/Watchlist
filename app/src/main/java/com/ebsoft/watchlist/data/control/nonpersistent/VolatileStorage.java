package com.ebsoft.watchlist.data.control.nonpersistent;

import android.arch.lifecycle.MutableLiveData;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.List;
import java.util.Map;

public interface VolatileStorage {

    MutableLiveData<List<Watchlist>> getWatchlistLiveData();

    MutableLiveData<Map<String, List<Stock>>> getStockMapLiveData();
}
