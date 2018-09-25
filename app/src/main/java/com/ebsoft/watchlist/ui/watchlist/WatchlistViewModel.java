package com.ebsoft.watchlist.ui.watchlist;

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

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 21/09/18.
 */

public class WatchlistViewModel extends BaseViewModel<WatchlistNavigator> {

    private final ObservableList<Stock> list = new ObservableArrayList<>();

    public WatchlistViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void loadStocks(LifecycleOwner owner, Watchlist watchlist) {
         getCompositeDisposable().add(mDataManager.getDbManager()
                 .loadStocks(watchlist)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(listLiveData -> listLiveData.observe(owner, stocks -> {
                     list.clear();
                     list.addAll(stocks);
                 })));
    }

    public void getQuote(String symbol) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(symbol, stock -> {
                    updateStock(stock);
                }));
    }

    public void insertStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .saveStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    private void updateStock(Stock updatedStock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .queryStock(updatedStock.getSymbol())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    for (Stock stock : stocks) {
                        stock.copy(updatedStock);
                        insertStock(stock);
                    }
                }));
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public ObservableList<Stock> getList() {
        return list;
    }
}
