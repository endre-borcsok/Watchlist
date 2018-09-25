package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.db.Stock;

import io.reactivex.disposables.Disposable;

/**
 * Created by endre on 09/09/18.
 */

public interface APIManager {

    Disposable searchSymbol(String symbol, SymbolSearchListener listener);

    Disposable getQuote(Stock stock, QuoteQueryListener listener);
}
