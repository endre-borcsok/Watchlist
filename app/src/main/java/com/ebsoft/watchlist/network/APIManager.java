package com.ebsoft.watchlist.network;

import android.support.annotation.NonNull;

import com.ebsoft.watchlist.data.model.db.Stock;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by endre on 09/09/18.
 */

public interface APIManager {

    Disposable searchSymbol(@NonNull String symbol, @NonNull SymbolSearchListener listener);

    Disposable getQuote(@NonNull Stock stock, @NonNull QuoteQueryListener listener);

    Disposable getBatchQuote(@NonNull List<Stock> stocks, @NonNull QuoteQueryListener listener);
}
