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

    private final ObservableList<Stock> list = new ObservableArrayList<>();

    public WatchlistViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void loadStocks(Watchlist watchlist) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .queryWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    list.clear();
                    for (Stock stock : stocks) {
                        list.add(stock);
                    }
                }));
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public void upsertStock(Stock stock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .upsertStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (!list.contains(stock.symbol)) {
                        list.add(stock);
                    }
                }));
    }

    public void getQuote(String symbol) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(symbol, stock -> {
                    updateStock(stock);
                }));
    }

    private void updateStock(Stock updatedStock) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .querySymbol(updatedStock.symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    for (Stock stock : stocks) {
                        stock.update(updatedStock);
                        upsertStock(stock);
                    }
                }));
    }

    public ObservableList<Stock> getList() {
        return list;
    }
}
