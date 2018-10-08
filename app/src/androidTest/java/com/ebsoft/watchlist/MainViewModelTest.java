package com.ebsoft.watchlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.DataManagerImpl;
import com.ebsoft.watchlist.data.local.db.DBManagerImpl;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.main.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by endre on 07/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    private MainViewModel mMainViewModel;

    private DataManager mDataManager;

    private List<Watchlist> mTestWatchlist;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        mDataManager = mock(DataManagerImpl.class);
        mMainViewModel = new MainViewModel(mDataManager);
    }

    @After
    public void release() {
        mMainViewModel = null;
        mDataManager = null;
        mTestWatchlist = null;
    }

    @Test
    public void testListLoads() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        MutableLiveData<List<Watchlist>> ld = new MutableLiveData<>();
        MutableLiveData<List<Stock>> ld1 = new MutableLiveData<>();
        LifecycleOwner lo = mock(LifecycleOwner.class);
        LifecycleRegistry lr = new LifecycleRegistry(lo);

        when(mDataManager.getDbManager()).thenReturn(mock(DBManagerImpl.class));
        when(mDataManager.getDbManager().loadWatchlists()).thenReturn(Observable.just(ld));
        when(mDataManager.getDbManager().loadStocks(any(Watchlist.class)))
                .thenReturn(Observable.just(ld1));
        when(lo.getLifecycle()).thenReturn(lr);
        lr.handleLifecycleEvent(Lifecycle.Event.ON_START);

        mMainViewModel.loadWatchlists(lo);
        ld.postValue(getTestWatchList());
        ld1.postValue(getTestStockList());
        latch.await(2, TimeUnit.SECONDS);
        assertTrue(mMainViewModel.getList().size() == getTestWatchList().size());
    }

    @Test
    public void testListNotNull() {
        assertTrue(mMainViewModel.getList() != null);
    }

    private List<Watchlist> getTestWatchList() {
        if (mTestWatchlist == null) {
            mTestWatchlist = new ArrayList<>();
            mTestWatchlist.add(new Watchlist("wl1"));
            mTestWatchlist.add(new Watchlist("wl2"));
        }
        return mTestWatchlist;
    }

    private List<Stock> getTestStockList() {
        List<Stock> list = new ArrayList<>();
        list.add(new Stock());
        list.add(new Stock());
        return list;
    }
}
