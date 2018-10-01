package com.ebsoft.watchlist.ui.watchlist;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import org.apache.commons.lang3.mutable.MutableBoolean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 21/09/18.
 */

public class WatchlistViewModel extends BaseViewModel<WatchlistNavigator> {

    private final ObservableList<Stock> list = new ObservableArrayList<>();

    private final Watchlist mWatchlist;

    public WatchlistViewModel(DataManager DataManager, Watchlist watchlist) {
        super(DataManager);
        this.mWatchlist = watchlist;
    }

    public Watchlist getWatchlist() {
        return mWatchlist;
    }

    public void removeStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .deleteStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void loadStocks(LifecycleOwner owner) {
         getCompositeDisposable().add(mDataManager.getDbManager()
                 .loadStocks(mWatchlist)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(listLiveData -> {
                     MutableBoolean refreshOnFirstLoad = new MutableBoolean(true);
                     listLiveData.observe(owner, stocks -> {
                         list.clear();
                         list.addAll(stocks);
                         if (refreshOnFirstLoad.getValue()) {
                             refreshOnFirstLoad.setValue(false);
                             refresh();
                         }
                     });
                 }));
    }

    public void insertStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .insertStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> getQuote(stock)));
    }

    public void getQuote(Stock stock) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(stock, () -> updateStock(stock)));
    }

    public void refresh() {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getBatchQuote(list, () -> {
                    for (Stock stock : list) {
                        updateStock(stock);
                    }
                }));
    }

    private void updateStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
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
}
