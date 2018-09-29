package com.ebsoft.watchlist.ui.watchlist;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
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
                 .subscribe(listLiveData -> listLiveData.observe(owner, stocks -> {
                     list.clear();
                     list.addAll(stocks);
                 })));
    }

    public void saveStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .saveStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> getQuote(stock)));
    }

    public void getQuote(Stock stock) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(stock, stock1 -> {
                    updateStock(stock1);
                }));
    }

    private void updateStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .saveStock(stock)
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
