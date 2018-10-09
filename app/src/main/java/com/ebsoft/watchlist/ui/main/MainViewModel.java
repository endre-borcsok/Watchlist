package com.ebsoft.watchlist.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.Nullable;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableList<Watchlist> list = new ObservableArrayList<>();

    private final LiveData<List<Watchlist>> mWatchlist;

    public MainViewModel(DataManager DataManager) {
        super(DataManager);
        mWatchlist = mDataManager.getDbManager().loadWatchlists();
    }

    public void deleteWatchlist(Watchlist watchlist) {
        addDisposable(mDataManager.getDbManager()
                .deleteWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void sundcribeForLiveData(LifecycleOwner owner) {
        mWatchlist.observe(owner, watchlists -> {
            loadStockLists(watchlists, owner);
        });
    }

    private void loadStockLists(List<Watchlist> watchlists, LifecycleOwner owner) {
        for (Watchlist wlist : watchlists) {
            mDataManager.getDbManager()
                    .loadStocks(wlist)
                    .observe(owner, stocks -> {
                        wlist.setStockCount(stocks.size());
                        list.clear();
                        list.addAll(watchlists);
                    });
        }
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public ObservableList<Watchlist> getList() {
        return list;
    }
}
