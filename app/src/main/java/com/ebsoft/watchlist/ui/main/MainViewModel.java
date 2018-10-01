package com.ebsoft.watchlist.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.Iterator;
import java.util.List;

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

    public void deleteWatchlist(Watchlist watchlist) {
        getCompositeDisposable().add(
                mDataManager.getDbManager()
                        .deleteWatchlist(watchlist)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(watchlists -> {
                            list.remove(watchlist);
                            removeStocks(watchlist);
                        }));
    }

    private void removeStocks(Watchlist watchlist) {
        for (Stock stock : watchlist.getStocks()) {
            getCompositeDisposable().add(
                    mDataManager.getDbManager()
                            .deleteStock(stock)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe());
        }
    }

    public void loadWatchlists(LifecycleOwner owner) {
        getCompositeDisposable().add(
                mDataManager.getDbManager()
                        .loadWatchlists()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(watchlists -> {
                            watchlists.observe(owner, watchlists1 -> {
                                list.clear();
                                list.addAll(watchlists1);
                                loadStockLists(watchlists1, owner);
                            });
                        }));
    }

    private void loadStockLists(List<Watchlist> watchlists, LifecycleOwner owner) {
        list.clear();
        loadNextStockList(watchlists.iterator(), owner);
    }

    private void loadNextStockList(Iterator it, LifecycleOwner owner) {
        if (it.hasNext()) {
            Watchlist wlist = (Watchlist) it.next();
            getCompositeDisposable().add(mDataManager.getDbManager()
                    .loadStocks(wlist)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(listLiveData -> listLiveData.observe(owner, stocks -> {
                        wlist.getStocks().clear();
                        wlist.getStocks().addAll(listLiveData.getValue());
                        list.remove(wlist);
                        list.add(wlist);
                        loadNextStockList(it, owner);
                    })));
        }
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public ObservableList<Watchlist> getList() {
        return list;
    }
}
