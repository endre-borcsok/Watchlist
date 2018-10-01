package com.ebsoft.watchlist.ui.search;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

/**
 * Created by endre on 09/09/18.
 */

public class SearchViewModel extends BaseViewModel {

    private final ObservableList<Item> list = new ObservableArrayList<>();

    public SearchViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void performSearch(String symbol) {
        getCompositeDisposable().add(getDataManager()
                .getApiManager()
                .searchSymbol(symbol, list -> {
                    this.list.clear();
                    this.list.addAll(list);
                }));
    }

    public ObservableList<Item> getList() {
        return list;
    }
}
