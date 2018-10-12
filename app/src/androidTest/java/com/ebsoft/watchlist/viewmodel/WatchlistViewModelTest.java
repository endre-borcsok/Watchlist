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
import com.ebsoft.watchlist.data.control.network.AlphaVantage.AlphaVantageAPI;
import com.ebsoft.watchlist.data.model.AlphaVantage.AVQuote;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.watchlist.WatchlistViewModel;
import com.ebsoft.watchlist.util.DbManagerUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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

    @Mock
    AlphaVantageAPI avApi;

    @InjectMocks
    APIManager apiManager = new APIManagerImpl();

    @Before
    public void setup() {
        when(avApi.getQuote(any(String.class)))
                .thenReturn(Observable.just(Response.success(new AVQuote())));
    }

    @Test
    public void testStockDeletion() throws InterruptedException {
        WatchlistViewModel watchlistViewModel = new WatchlistViewModel(mockDataManager(),
                new Watchlist(new String()));
        subscribeToLiveData(watchlistViewModel);
        insertStock(watchlistViewModel);
        removeStock(watchlistViewModel);
        assertTrue(watchlistViewModel.getList().isEmpty());
    }

    @Test
    public void testStockInsertion() throws InterruptedException {
        WatchlistViewModel watchlistViewModel = new WatchlistViewModel(mockDataManager(),
                new Watchlist(new String()));
        subscribeToLiveData(watchlistViewModel);
        insertStock(watchlistViewModel);
        assertTrue(!watchlistViewModel.getList().isEmpty());
    }

    @Test
    public void testSubscribeToLiveData() throws InterruptedException {
        WatchlistViewModel watchlistViewModel = new WatchlistViewModel(mockDataManager(),
                new Watchlist(new String()));
        subscribeToLiveData(watchlistViewModel);
        assertTrue(watchlistViewModel.getList().isEmpty());
    }

    @Test
    public void testWatchlistNotNull() {
        assertTrue(new WatchlistViewModel(mock(DataManager.class),
                new Watchlist(new String())).getWatchlist() != null);
    }

    @Test
    public void testListNotNull() {
        assertTrue(new WatchlistViewModel(mock(DataManager.class),
                new Watchlist(new String())).getList() != null);
    }

    private void subscribeToLiveData(WatchlistViewModel watchlistViewModel)
            throws InterruptedException {
        watchlistViewModel.subscribeToLiveData(mockLifecycleOwner());
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
    }

    private void insertStock(WatchlistViewModel watchlistViewModel) throws InterruptedException {
        watchlistViewModel.insertStock(new Stock());
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
        DataManager dataMAnager = mock(DataManager.class);
        AbstractDataBase db = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        DBManagerImpl dbManager = new DBManagerImpl(db);
        when(dataMAnager.getDbManager()).thenReturn(dbManager);
        when(dataMAnager.getApiManager()).thenReturn(apiManager);
        return dataMAnager;
    }
}