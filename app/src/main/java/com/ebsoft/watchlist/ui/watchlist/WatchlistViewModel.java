package com.ebsoft.watchlist.ui.watchlist;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.Iterator;

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

    public void getQuote(String symbol) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(symbol, stock -> {
                    updateStock(stock);
                }));
    }

    public void loadStocks(Watchlist watchlist) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .queryWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    list.clear();
                    list.addAll(stocks);
                }));
    }

    public void insertStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .insertStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    list.add(stock);
                }));
    }

    private void updateStock(Stock updatedStock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .querySymbol(updatedStock.getSymbol())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    for (Stock stock : stocks) {
                        stock.update(updatedStock);
                        upsertStock(stock);
                    }
                }));
    }

    private void upsertStock(Stock updatedStock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .insertStock(updatedStock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    for (Stock stock : list) {
                        if (stock.getSymbol().equals(updatedStock.getSymbol())) {
                            stock.update(updatedStock);
                            break;
                        }
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
