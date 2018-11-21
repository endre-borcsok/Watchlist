package com.ebsoft.watchlist.viewmodel;

import android.support.test.InstrumentationRegistry;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.control.db.AbstractDataBase;
import com.ebsoft.watchlist.data.control.db.DBManagerImpl;
import com.ebsoft.watchlist.data.control.network.APIManager;
import com.ebsoft.watchlist.data.control.network.APIManagerImpl;
import com.ebsoft.watchlist.data.control.network.IEX.IEXApi;
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.yahoo.Item;
import com.ebsoft.watchlist.data.model.yahoo.SymbolSearch;
import com.ebsoft.watchlist.data.model.yahoo.SymbolSearchResponse;
import com.ebsoft.watchlist.ui.search.SearchViewModel;
import com.ebsoft.watchlist.util.DbManagerUtil;
import com.ebsoft.watchlist.util.KotlinUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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

    private YahooAPI yahooAPI;

    private APIManager apiManager;

    @Before
    public void setup() {
        yahooAPI = mock(YahooAPI.class);
        apiManager =  new APIManagerImpl(yahooAPI, mock(IEXApi.class));
    }

    @After
    public void clear() {
        yahooAPI = null;
        apiManager = null;
    }

    @Test
    public void testSearchSuccessful() throws InterruptedException {
        SearchViewModel searchViewModel = new SearchViewModel(mockDataManager());
        searchViewModel.performSearch(new String());
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
        assertTrue(!searchViewModel.getList().isEmpty());
    }

    @Test
    public void testStockInsertion() throws InterruptedException {
        SearchViewModel searchViewModel = new SearchViewModel(mockDataManager());
        Stock stock = new Stock();
        stock.setSymbol("AAA");
        searchViewModel.insertStock(stock);
        new CountDownLatch(1).await(1, TimeUnit.SECONDS);
        searchViewModel.getDataManager().getDbManager()
                .queryStock("AAA").subscribe(stocks -> assertTrue(!stocks.isEmpty()));
    }

    @Test
    public void testListNotNull() {
        assertTrue(new SearchViewModel(mock(DataManager.class)).getList() != null);
    }

    private DataManager mockDataManager() {
        DataManager dataManager = mock(DataManager.class);
        AbstractDataBase db = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        DBManagerImpl dbManager = new DBManagerImpl(db);
        when(dataManager.getDbManager()).thenReturn(dbManager);
        when(dataManager.getApiManager()).thenReturn(apiManager);
        when(yahooAPI.searchSymbol(any(String.class)))
                .thenReturn(KotlinUtils.mockDeferred(Response.success(getResponse())));
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
