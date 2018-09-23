package com.ebsoft.watchlist.ui.search;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.Yahoo.SymbolSearch;
import com.ebsoft.watchlist.data.model.Yahoo.SymbolSearchResponse;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .subscribe(symbolSearch -> {
                    SymbolSearch.processResponse(symbolSearch, list);
                })
        );
    }

    public ObservableList<String> getList() {
        return list;
    }
}
