package com.ebsoft.watchlist.ui.watchlist;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableArrayList;
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

    public void getQuote(Stock stock) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(stock, stock1 -> {
                    insertStock(stock1);
                }));
    }

    public void insertStock(Stock stock) {
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
