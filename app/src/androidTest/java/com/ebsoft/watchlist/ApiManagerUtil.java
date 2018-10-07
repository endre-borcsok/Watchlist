package com.ebsoft.watchlist;

import com.ebsoft.watchlist.data.model.yahoo.SymbolSearch;
import com.ebsoft.watchlist.network.APIManager;
import com.ebsoft.watchlist.network.APIManagerImpl;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by endre on 07/10/18.
 */

public class ApiManagerUtil {

    public static APIManager getMock() {
        APIManager apiManager = mock(APIManagerImpl.class);
        when(apiManager.searchSymbol(null, null)).thenReturn(getMockDisposable());
        when(apiManager.getBatchQuote(null, null)).thenReturn((getMockDisposable()));
        when(apiManager.getQuote(null, null)).thenReturn(getMockDisposable());
        return apiManager;
    }

    private static Disposable getMockDisposable() {
        Disposable disposable = mock(Disposable.class);
        return disposable;
    }
}
