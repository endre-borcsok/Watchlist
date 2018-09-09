package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.Yahoo.SymbolSearch;
import com.ebsoft.watchlist.network.Yahoo.YahooApiFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by endre on 09/09/18.
 */

@Singleton
public class APIManagerImpl implements APIManager {

    @Inject
    APIManagerImpl() {}

    @Override
    public Observable<Response<SymbolSearch>> searchSymbol(String symbol) {
        return YahooApiFactory.createYahooAPI().searchSymbol(symbol);
    }
}
