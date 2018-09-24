package com.ebsoft.watchlist.ui.watchlist;

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

    private final ObservableList<String> list = new ObservableArrayList<>();

    public WatchlistViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void loadStocks(Watchlist watchlist) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .loadStocksForWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    list.clear();
                    for (Stock stock : stocks) {
                        list.add(stock.symbol);
                    }
                }));
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public void insertStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .insertStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    list.add(stock.symbol);
                }));
    }

    public void getQuote(String symbol) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(symbol, stock -> {

                }));
    }

    private void updateStock(Stock newStock) {
        Stock stock = mDataManager.getDbManager().
    }

    public ObservableList<String> getList() {
        return list;
    }
}
