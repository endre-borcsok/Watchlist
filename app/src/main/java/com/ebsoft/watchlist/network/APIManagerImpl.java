package com.ebsoft.watchlist.network;

import android.support.annotation.NonNull;

import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.network.Yahoo.YahooApiFactory;

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
    APIManagerImpl() {}

    @Override
    public Disposable searchSymbol(String symbol, List<String> targetList) {
        return YahooApiFactory.createYahooAPI().searchSymbol(symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(symbolSearchResponse -> {
                    List<Item> items = symbolSearchResponse.body()
                            .getSymbolSearchResponse()
                            .getItems();
                    targetList.clear();
                    for (Item item : items) {
                        if (item.getType().equals("S")) {
                            targetList.add(item.getSymbol());
                        }
                    }
                });
    }
}
