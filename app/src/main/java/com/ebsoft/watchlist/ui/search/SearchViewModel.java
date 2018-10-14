package com.ebsoft.watchlist.ui.search;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.yahoo.Item;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 09/09/18.
 */

public class SearchViewModel extends BaseViewModel {

    private final ObservableList<Item> list = new ObservableArrayList<>();

    public SearchViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void performSearch(String symbol) {
        addDisposable(getDataManager()
                .getApiManager()
                .searchSymbol(symbol, list -> {
                    this.list.clear();
                    this.list.addAll(list);
                }));
    }

    public void insertStock(Stock stock) {
        addDisposable(mDataManager.getDbManager()
                .insertStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public ObservableList<Item> getList() {
        return list;
    }
}
