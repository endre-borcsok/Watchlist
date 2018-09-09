package com.ebsoft.watchlist.ui.main;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.Result;
import com.ebsoft.watchlist.data.model.ResultSet;
import com.ebsoft.watchlist.data.model.YahooSymbolLookup;
import com.ebsoft.watchlist.network.APIManager;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel {

    private final ObservableList<String> list = new ObservableArrayList<>();

    public MainViewModel(DataManager dataManager, APIManager apiManager) {
        super(dataManager, apiManager);
    }

    public void performSymbolSearch() {
        getCompositeDisposable().add(
                mApiManager.searchSymbol("AAPL")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(YahooSymbolSearchResponse -> {
                            Log.d("res:", YahooSymbolSearchResponse.body().toString());
                            List<Result> results = YahooSymbolSearchResponse.body().getResultSet().getResult();
                            for (Result result : results) {
                                list.add(result.getSymbol());
                            }
                        })
        );
    }

    public ObservableList<String> getList() {
        return list;
    }
}
