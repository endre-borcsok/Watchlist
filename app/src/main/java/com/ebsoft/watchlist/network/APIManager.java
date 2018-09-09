package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.Yahoo.SymbolSearch;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by endre on 09/09/18.
 */

public interface APIManager {

    Observable<Response<SymbolSearch>> searchSymbol(String symbol);
}
