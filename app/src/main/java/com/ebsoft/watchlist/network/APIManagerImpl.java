package com.ebsoft.watchlist.network;

import android.support.annotation.NonNull;

import com.ebsoft.watchlist.data.model.AlphaVantage.AVQuote;
import com.ebsoft.watchlist.data.model.AlphaVantage.GlobalQuote;
import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.network.AlphaVantage.AlphaVantageAPI;
import com.ebsoft.watchlist.network.Yahoo.YahooAPI;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by endre on 09/09/18.
 */

@Singleton
public class APIManagerImpl implements APIManager {

    @Inject
    YahooAPI mYahooApi;

    @Inject
    AlphaVantageAPI mAlphaVantageApi;

    @Inject
    APIManagerImpl() {}

    @Override
    public Disposable searchSymbol(@NonNull String symbol, @NonNull SymbolSearchListener listener) {
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
                    listener.onComplete(list);
                });
    }

    @Override
    public Disposable getQuote(String symbol, QuoteQueryListener listener) {
        return mAlphaVantageApi.getQuote(symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avQuoteResponse -> {
                    GlobalQuote gq = avQuoteResponse.body().getGlobalQuote();
                    Stock stock = new Stock(symbol, null);
                    stock.setPrice(Float.parseFloat(gq.getPrice()));
                    stock.setChange(Float.parseFloat(gq.getChange()));
                    stock.setChangePercent(Float.parseFloat(gq.getChangePercent()
                            .replace("%", "")));
                    listener.onComplete(stock);
                });
    }
}
