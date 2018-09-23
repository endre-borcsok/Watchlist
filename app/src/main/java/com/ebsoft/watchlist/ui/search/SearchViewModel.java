package com.ebsoft.watchlist.ui.search;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.data.model.db.Symbol;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 09/09/18.
 */

public class SearchViewModel extends BaseViewModel {

    private final ObservableList<String> list = new ObservableArrayList<>();

    public SearchViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void performSearch(String text) {
        getCompositeDisposable().add(getDataManager()
                .getApiManager()
                .searchSymbol(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(symbolSearchResponse -> {
                    List<Item> items = symbolSearchResponse.body()
                            .getSymbolSearchResponse()
                            .getItems();
                    processList(items);
                })
        );
    }

    private void processList(List<Item> items) {
        if (items != null) {
            list.clear();
            for (Item item : items) {
                list.add(item.getSymbol());
            }
        }
    }

    public ObservableList<String> getList() {
        return list;
    }
}
