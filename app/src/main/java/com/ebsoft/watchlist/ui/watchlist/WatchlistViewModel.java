package com.ebsoft.watchlist.ui.watchlist;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

/**
 * Created by endre on 21/09/18.
 */

public class WatchlistViewModel extends BaseViewModel {

    private final ObservableList<Watchlist> list = new ObservableArrayList<>();

    public WatchlistViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void loadSymbols() {
        //TODO
    }

    public void onActionButtonClick() {
        //TODO
    }

    public ObservableList<Watchlist> getList() {
        return list;
    }
}
