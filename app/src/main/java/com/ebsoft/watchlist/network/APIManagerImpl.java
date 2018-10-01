package com.ebsoft.watchlist.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ebsoft.watchlist.data.model.AlphaVantage.AVQuote;
import com.ebsoft.watchlist.data.model.AlphaVantage.GlobalQuote;
import com.ebsoft.watchlist.data.model.IEX.Quote;
import com.ebsoft.watchlist.data.model.IEX.StockQuote;
import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.network.AlphaVantage.AlphaVantageAPI;
import com.ebsoft.watchlist.network.IEX.IEXApi;
import com.ebsoft.watchlist.network.Yahoo.YahooAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    IEXApi mIEXApi;

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
    public Disposable getQuote(Stock stock, QuoteQueryListener listener) {
        return mAlphaVantageApi.getQuote(stock.getSymbol())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(avQuoteResponse -> {
                    GlobalQuote gq = avQuoteResponse.body().getGlobalQuote();
                    stock.setPrice(Float.parseFloat(gq.getPrice()));
                    stock.setChange(Float.parseFloat(gq.getChange()));
                    stock.setChangePercent(Float.parseFloat(gq.getChangePercent()
                            .replace("%", "")));
                    listener.onComplete();
                });
    }

    @Override
    public Disposable getBatchQuote(List<Stock> stocks, QuoteQueryListener listener) {
        return mIEXApi.getQuote(getSymbols(stocks))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mapResponse -> {
                    Map<String, StockQuote> quoteMap = mapResponse.body();
                    for (Stock stock : stocks) {
                        StockQuote stockQuote = quoteMap.get(stock.getSymbol());
                        if (stockQuote != null) {
                            Quote quote = stockQuote.getQuote();
                            stock.setPrice(quote.getLatestPrice().floatValue());
                            stock.setChangePercent(quote.getChangePercent().floatValue());
                            stock.setChange(quote.getChange().floatValue());
                        }
                    }
                    listener.onComplete();
                });
    }

    private String getSymbols(List<Stock> stocks) {
        StringBuilder sb = new StringBuilder();
        for (Stock stock : stocks) {
            sb.append(stock.getSymbol());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
