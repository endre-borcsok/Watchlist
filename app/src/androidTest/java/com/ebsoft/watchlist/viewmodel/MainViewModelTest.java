package com.ebsoft.watchlist.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.databinding.ObservableList;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by endre on 07/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    @Test
    public void testListLoads() throws InterruptedException {
        assertTrue(loadList(
                new MainViewModelTestCase()).size() == getTestWatchList().size());
    }

    @Test
    public void testListNotNull() {
        assertTrue(new MainViewModelTestCase().getViewModel().getList() != null);
    }

    private ObservableList<Watchlist> loadList(MainViewModelTestCase test)
            throws InterruptedException {
        test.getViewModel().sundcribeForLiveData(mockLifecycleOwner());
        test.getWatchlistLiveData().postValue(getTestWatchList());
        test.getStockListLiveData().postValue(getTestStockList());
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
        return test.getViewModel().getList();
    }

    private LifecycleOwner mockLifecycleOwner() {
        LifecycleOwner lo = mock(LifecycleOwner.class);
        LifecycleRegistry lr = new LifecycleRegistry(lo);
        when(lo.getLifecycle()).thenReturn(lr);
        lr.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return lo;
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
