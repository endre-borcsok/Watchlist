package com.ebsoft.watchlist.ui.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableList<Watchlist> list = new ObservableArrayList<>();

    public MainViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void loadWatchlists() {
        getCompositeDisposable().add(
                mDataManager.getDbManager().queryWatchlists()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(watchlists -> {
                            list.clear();
                            list.addAll(watchlists);
                        }));
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public ObservableList<Watchlist> getList() {
        return list;
    }
}
