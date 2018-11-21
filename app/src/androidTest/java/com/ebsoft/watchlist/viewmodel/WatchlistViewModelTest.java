package com.ebsoft.watchlist.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.test.InstrumentationRegistry;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.control.db.AbstractDataBase;
import com.ebsoft.watchlist.data.control.db.DBManagerImpl;
import com.ebsoft.watchlist.data.control.network.APIManager;
import com.ebsoft.watchlist.data.control.network.APIManagerImpl;
import com.ebsoft.watchlist.data.control.network.IEX.IEXApi;
import com.ebsoft.watchlist.data.control.network.Yahoo.YahooAPI;
import com.ebsoft.watchlist.data.model.IEX.StockQuote;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.watchlist.WatchlistViewModel;
import com.ebsoft.watchlist.util.DbManagerUtil;
import com.ebsoft.watchlist.util.KotlinUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by endre on 12/10/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class WatchlistViewModelTest {

    private IEXApi iexApi;

    private APIManager apiManager;

    @Before
    public void setup() {
        iexApi = mock(IEXApi.class);
        apiManager = new APIManagerImpl(mock(YahooAPI.class), iexApi);
        when(iexApi.getQuote(any(String.class)))
                .thenReturn(KotlinUtils.mockDeferred(Response.success(getResponseMap())));
    }

    @After
    public void clear() {
        iexApi = null;
        apiManager = null;
    }

    @Test
    public void testStockDeletion() throws InterruptedException {
        WatchlistViewModel watchlistViewModel = new WatchlistViewModel(mockDataManager(),
                getWatchlist());
        subscribeToLiveData(watchlistViewModel);
        insertStock(watchlistViewModel);
        removeStock(watchlistViewModel);
        assertTrue(watchlistViewModel.getList().isEmpty());
    }

    @Test
    public void testStockInsertion() throws InterruptedException {
        WatchlistViewModel watchlistViewModel = new WatchlistViewModel(mockDataManager(),
                getWatchlist());
        subscribeToLiveData(watchlistViewModel);
        insertStock(watchlistViewModel);
        assertTrue(!watchlistViewModel.getList().isEmpty());
    }

    @Test
    public void testSubscribeToLiveData() throws InterruptedException {
        WatchlistViewModel watchlistViewModel = new WatchlistViewModel(mockDataManager(),
                getWatchlist());
        subscribeToLiveData(watchlistViewModel);
        assertTrue(watchlistViewModel.getList().isEmpty());
    }

    @Test
    public void testWatchlistNotNull() {
        assertTrue(new WatchlistViewModel(mock(DataManager.class),
                getWatchlist()).getWatchlist() != null);
    }

    @Test
    public void testListNotNull() {
        assertTrue(new WatchlistViewModel(mock(DataManager.class),
                getWatchlist()).getList() != null);
    }

    private void subscribeToLiveData(WatchlistViewModel watchlistViewModel)
            throws InterruptedException {
        watchlistViewModel.subscribeToLiveData(mockLifecycleOwner());
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
    }

    private void insertStock(WatchlistViewModel watchlistViewModel) throws InterruptedException {
        Stock newStock = new Stock();
        newStock.setListid(watchlistViewModel.getWatchlist().getId());
        watchlistViewModel.getDataManager().getDbManager().insertStock(newStock).subscribe();
        new CountDownLatch(1).await(1, TimeUnit.SECONDS);
    }

    private void removeStock(WatchlistViewModel watchlistViewModel) throws InterruptedException {
        watchlistViewModel.removeStock(watchlistViewModel.getList().get(0));
        new CountDownLatch(1).await(1, TimeUnit.SECONDS);
    }

    private LifecycleOwner mockLifecycleOwner() {
        LifecycleOwner lo = mock(LifecycleOwner.class);
        LifecycleRegistry lr = new LifecycleRegistry(lo);
        when(lo.getLifecycle()).thenReturn(lr);
        lr.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return lo;
    }

    private DataManager mockDataManager() {
        DataManager dataManager = mock(DataManager.class);
        AbstractDataBase db = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        DBManagerImpl dbManager = new DBManagerImpl(db);
        when(dataManager.getDbManager()).thenReturn(dbManager);
        when(dataManager.getApiManager()).thenReturn(apiManager);
        return dataManager;
    }

    private Map<String, StockQuote> getResponseMap() {
        Map<String, StockQuote> map = new HashMap<>();
        map.put("AAPL", new StockQuote());
        map.put("MSFT", new StockQuote());
        return map;
    }

    private Watchlist getWatchlist() {
        Watchlist wlist = new Watchlist("test");
        wlist.setId(1);
        return wlist;
    }
}
