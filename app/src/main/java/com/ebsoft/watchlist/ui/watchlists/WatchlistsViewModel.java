package com.ebsoft.watchlist.ui.watchlists;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 07/09/18.
 */

public class WatchlistsViewModel extends BaseViewModel<WatchlistsNavigator> {

    private final ObservableList<Watchlist> list = new ObservableArrayList<>();

    public WatchlistsViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void subscribeToLiveData(LifecycleOwner owner) {
        mDataManager.getDbManager().loadWatchlists().observe(owner, watchlists -> {
            list.clear();
            list.addAll(watchlists);
            subscribeToStockCountObserver(owner, watchlists);
        });
    }

    public void subscribeToStockCountObserver(LifecycleOwner owner, List<Watchlist> watchlists) {
        for (Watchlist wlist : watchlists) {
            mDataManager.getDbManager().loadStocks(wlist)
                    .observe(owner, stocks -> {
                        wlist.getStockCountObservable().set(stocks.size());
                    });
        }
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
