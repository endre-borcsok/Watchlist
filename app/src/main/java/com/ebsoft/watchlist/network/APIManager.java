package com.ebsoft.watchlist.network;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by endre on 09/09/18.
 */

public interface APIManager {

    Disposable searchSymbol(String symbol, List<String> targetList);
}
