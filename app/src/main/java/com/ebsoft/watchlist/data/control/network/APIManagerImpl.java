package com.ebsoft.watchlist.data.control.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ebsoft.watchlist.data.control.network.IEX.IEXApi;
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI;
import com.ebsoft.watchlist.data.model.IEX.StockQuote;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.yahoo.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private final String TAG = APIManagerImpl.class.getSimpleName();

    @Inject
    YahooAPI mYahooApi;

    @Inject
    IEXApi mIEXApi;

    @Inject
    public APIManagerImpl() {}

    @Override
    public Disposable searchSymbol(@NonNull String symbol, @NonNull SymbolSearchListener listener) {
        return mYahooApi.searchSymbol(symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(symbolSearchResponse -> {
                    List<Item> items = symbolSearchResponse.body()
                            .getSymbolSearchResponse()
                            .getItems();
                    listener.onComplete(items);
                }, throwable -> {
                    Log.e(TAG, throwable.getLocalizedMessage());
                    listener.onComplete(new ArrayList<>());
                });
    }

    @Override
    public Disposable getBatchQuote(@NonNull List<Stock> stocks, @NonNull QuoteQueryListener listener) {
        return mIEXApi.getQuote(getSymbols(stocks))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mapResponse -> {
                    Map<String, StockQuote> quoteMap = mapResponse.body();
                    for (Stock stock : stocks) {
                        StockQuote stockQuote = quoteMap.get(stock.getSymbol());
                        if (stockQuote != null) {
                            stock.update(stockQuote.getQuote());
                        }
                    }
                    listener.onComplete();
                }, throwable -> {
                    Log.e(TAG, throwable.getLocalizedMessage());
                    listener.onComplete();
                });
    }

    private String getSymbols(List<Stock> stocks) {
        StringBuilder sb = new StringBuilder();
        for (Stock stock : stocks) {
            sb.append(',');
            sb.append(stock.getSymbol());
        }
        if (sb.length() > 0) sb.deleteCharAt(0);
        return sb.toString();
    }
}
