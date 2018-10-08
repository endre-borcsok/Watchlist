package com.ebsoft.watchlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableList;
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

    @Test
    public void testListLoads() throws InterruptedException {
        List<Watchlist> testList = getTestWatchList();
        assertTrue(loadList(testList).size() == testList.size());
    }

    @Test
    public void testListNotNull() {
        assertTrue(new MainViewModel(mock(DataManager.class)).getList() != null);
    }

    private ObservableList<Watchlist> loadList(List<Watchlist> list) throws InterruptedException {
        DataManager dataManager = mock(DataManager.class);
        MainViewModel mainViewModel = new MainViewModel(dataManager);
        CountDownLatch latch = new CountDownLatch(1);
        MutableLiveData<List<Watchlist>> ld = new MutableLiveData<>();
        MutableLiveData<List<Stock>> ld1 = new MutableLiveData<>();
        LifecycleOwner lo = mock(LifecycleOwner.class);
        LifecycleRegistry lr = new LifecycleRegistry(lo);

        when(dataManager.getDbManager()).thenReturn(mock(DBManagerImpl.class));
        when(dataManager.getDbManager().loadWatchlists()).thenReturn(Observable.just(ld));
        when(dataManager.getDbManager().loadStocks(any(Watchlist.class)))
                .thenReturn(Observable.just(ld1));
        when(lo.getLifecycle()).thenReturn(lr);
        lr.handleLifecycleEvent(Lifecycle.Event.ON_START);

        mainViewModel.loadWatchlists(lo);
        ld.postValue(list);
        ld1.postValue(getTestStockList());
        latch.await(2, TimeUnit.SECONDS);
        return mainViewModel.getList();
    }

    private List<Watchlist> getTestWatchList() {
        List<Watchlist> list = new ArrayList<>();
        list.add(new Watchlist("wl1"));
        list.add(new Watchlist("wl2"));
        return list;
    }

    private List<Stock> getTestStockList() {
        List<Stock> list = new ArrayList<>();
        list.add(new Stock());
        list.add(new Stock());
        return list;
    }
}
