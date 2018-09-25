package com.ebsoft.watchlist.ui.watchlist;

import android.arch.lifecycle.MutableLiveData;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 21/09/18.
 */

public class WatchlistViewModel extends BaseViewModel<WatchlistNavigator> {

    private final MutableLiveData<List<Stock>> list = new MutableLiveData();

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
                .loadStocks(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    list.setValue(stocks);
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
                        stock.update(updatedStock);
                        insertStock(stock);
                    }
                }));
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public MutableLiveData<List<Stock>> getList() {
        return list;
    }
}
