package com.ebsoft.watchlist.viewmodel;

import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.control.network.APIManager;
import com.ebsoft.watchlist.data.control.network.APIManagerImpl;
import com.ebsoft.watchlist.data.control.network.SymbolSearchListener;
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI;
import com.ebsoft.watchlist.data.model.yahoo.Item;
import com.ebsoft.watchlist.data.model.yahoo.SymbolSearch;
import com.ebsoft.watchlist.data.model.yahoo.SymbolSearchResponse;
import com.ebsoft.watchlist.ui.search.SearchViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by endre on 10/10/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchViewModelTest {

    @Mock
    YahooAPI yahooAPI;

    @InjectMocks
    APIManager apiManager = new APIManagerImpl();

    @Test
    public void testSearchSuccessful() throws InterruptedException {
        SearchViewModel searchViewModel = new SearchViewModel(mockDataManager());
        searchViewModel.performSearch(new String());
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
        assertTrue(!searchViewModel.getList().isEmpty());
    }

    @Test
    public void testListNotNull() {
        assertTrue(new SearchViewModel(mock(DataManager.class)).getList() != null);
    }

    private DataManager mockDataManager() {
        DataManager dataManager = mock(DataManager.class);
        when(dataManager.getApiManager()).thenReturn(apiManager);
        when(yahooAPI.searchSymbol(any(String.class)))
                .thenReturn(Observable.just(Response.success(getResponse())));
        return dataManager;
    }

    private SymbolSearch getResponse() {
        return new SymbolSearch(new SymbolSearchResponse(new String(), getItems()));
    }

    private List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        list.add(new Item());
        list.add(new Item());
        return list;
    }
}
