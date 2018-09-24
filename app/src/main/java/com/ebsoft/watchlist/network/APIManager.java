package com.ebsoft.watchlist.network;

import io.reactivex.disposables.Disposable;

/**
 * Created by endre on 09/09/18.
 */

public interface APIManager {

    Disposable searchSymbol(String symbol, SymbolSearchListener listener);

    Disposable getQuote(String symbol, QuoteQueryListener listener);
}
