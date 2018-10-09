package com.ebsoft.watchlist.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableList<Watchlist> list = new ObservableArrayList<>();

    public MainViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void subscribeToLiveData(LifecycleOwner owner) {
        mDataManager.getDbManager().loadWatchlists().observe(owner, watchlists -> {
            for (Watchlist wlist : watchlists) {
                mDataManager.getDbManager().loadStocks(wlist)
                        .observe(owner, stocks -> {
                            wlist.setStockCount(stocks.size());
                            list.clear();
                            list.addAll(watchlists);
                        });
            }
        });
    }

    public void deleteWatchlist(Watchlist watchlist) {
        addDisposable(mDataManager.getDbManager()
                .deleteWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public ObservableList<Watchlist> getList() {
        return list;
    }
}
