package com.ebsoft.watchlist.ui.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.List;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel {

    private final ObservableList<String> list = new ObservableArrayList<>();

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void addItemsToList(List<String> items) {
        list.clear();
        list.addAll(items);
    }

    public ObservableList<String> getList() {
        return list;
    }
}
