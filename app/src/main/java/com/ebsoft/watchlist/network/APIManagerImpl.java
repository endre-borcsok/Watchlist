package com.ebsoft.watchlist.network;

import android.support.annotation.NonNull;

import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.network.Yahoo.YahooAPI;
import com.ebsoft.watchlist.network.Yahoo.YahooApiFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 09/09/18.
 */

@Singleton
public class APIManagerImpl implements APIManager {

    @Inject
    YahooAPI mYahooApi;

    @Inject
    APIManagerImpl() {}

    @Override
    public Disposable searchSymbol(@NonNull String symbol, @NonNull SymbolSearchListener mListener) {
        return mYahooApi.searchSymbol(symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(symbolSearchResponse -> {
                    List<String> list = new ArrayList<>();
                    List<Item> items = symbolSearchResponse.body()
                            .getSymbolSearchResponse()
                            .getItems();
                    for (Item item : items) {
                        if (item.getType().equals("S")) {
                            list.add(item.getSymbol());
                        }
                    }
                    mListener.onComplete(list);
                });
    }
}
