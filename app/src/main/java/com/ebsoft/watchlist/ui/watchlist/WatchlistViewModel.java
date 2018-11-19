package com.ebsoft.watchlist.ui.watchlist;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 21/09/18.
 */

public class WatchlistViewModel extends BaseViewModel<WatchlistNavigator> {

    private final ObservableList<Stock> list = new ObservableArrayList<>();

    private final ObservableBoolean loading = new ObservableBoolean();

    private final Watchlist mWatchlist;

    private boolean mRequestRefresh = false;

    public WatchlistViewModel(DataManager DataManager, Watchlist watchlist) {
        super(DataManager);
        this.mWatchlist = watchlist;
    }

    public Watchlist getWatchlist() {
        return mWatchlist;
    }

    public void subscribeToLiveData(LifecycleOwner owner) {
        mRequestRefresh = true;
        getDataManager().getDbManager().loadStocks(mWatchlist).observe(owner, stocks -> {
            list.clear();
            list.addAll(stocks);
            if (mRequestRefresh){
                mRequestRefresh = false;
                refresh();
            }
        });
    }

    public void removeStock(Stock stock) {
        addDisposable(getDataManager().getDbManager()
                .deleteStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void refresh() {
        if (list.size() < 1) return;
        loading.set(true);
        addDisposable(getDataManager().getApiManager()
                .getBatchQuote(list, () -> {
                    for (Stock stock : list) {
                        updateStock(stock);
                    }
                    loading.set(false);
                }));
    }

    private void updateStock(Stock stock) {
        addDisposable(getDataManager().getDbManager()
                .updateStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public ObservableList<Stock> getList() {
        return list;
    }

    public ObservableBoolean getLoading() {return loading;}
}
