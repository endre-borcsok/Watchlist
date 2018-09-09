package com.ebsoft.watchlist.ui.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel {

    private final ObservableList<String> list = new ObservableArrayList<>();

    public MainViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void performSymbolSearch() {
        getCompositeDisposable().add(
                mDataManager.getApiManager().searchSymbol("AAPL")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(SymbolSearch -> {
                            Log.d("res:", SymbolSearch.body().toString());
                            List<Item> items =
                                    SymbolSearch
                                            .body()
                                            .getSymbolSearchResponse()
                                            .getItems();
                            for (Item item : items) {
                                list.add(item.getSymbol());
                            }
                        })
        );
    }

    public ObservableList<String> getList() {
        return list;
    }
}
