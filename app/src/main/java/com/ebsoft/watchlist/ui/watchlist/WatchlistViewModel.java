package com.ebsoft.watchlist.ui.watchlist;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Symbol;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.network.QuoteQueryListener;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 21/09/18.
 */

public class WatchlistViewModel extends BaseViewModel<WatchlistNavigator> {

    private final ObservableList<String> list = new ObservableArrayList<>();

    public WatchlistViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public void loadSymbols(Watchlist watchlist) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .loadSymbolsForWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(symbols -> {
                    list.clear();
                    for (Symbol symbol : symbols) {
                        list.add(symbol.name);
                    }
                }));
    }

    public void onActionButtonClick() {
        getNavigator().onActionButtonClick();
    }

    public void insertSymbol(Symbol symbol) {
        getCompositeDisposable().add(mDataManager.getDbManager()
                .insertSymbol(symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    list.add(symbol.name);
                }));
    }

    public void getQuote(String symbol) {
        getCompositeDisposable().add(mDataManager.getApiManager()
                .getQuote(symbol, symbol1 -> {
                    //TODO
                }));
    }

    public ObservableList<String> getList() {
        return list;
    }
}
