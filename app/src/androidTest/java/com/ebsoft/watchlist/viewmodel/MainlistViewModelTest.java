package com.ebsoft.watchlist.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableList;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.control.db.DBManagerImpl;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.mainlist.MainlistViewModel;

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
public class MainlistViewModelTest {

    @Test
    public void testDeleteWatchlist() throws InterruptedException {
        MainlistViewModel viewModel = getViewModel();
        loadList(viewModel);
        viewModel.deleteWatchlist(new Watchlist(""));
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
        assertTrue(viewModel.getList().size() != getTestWatchList().size());
    }

    @Test
    public void testListLoads() throws InterruptedException {
        assertTrue(loadList(getViewModel()).size() == getTestWatchList().size());
    }

    @Test
    public void testListNotNull() {
        assertTrue(getViewModel().getList() != null);
    }

    private ObservableList<Watchlist> loadList(MainlistViewModel viewModel)
            throws InterruptedException {
        viewModel.subscribeToLiveData(mockLifecycleOwner());
        new CountDownLatch(1).await(2, TimeUnit.SECONDS);
        return viewModel.getList();
    }

    private LifecycleOwner mockLifecycleOwner() {
        LifecycleOwner lo = mock(LifecycleOwner.class);
        LifecycleRegistry lr = new LifecycleRegistry(lo);
        when(lo.getLifecycle()).thenReturn(lr);
        lr.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return lo;
    }

    private MainlistViewModel getViewModel() {
        return new MainlistViewModel(mockDataManager());
    }

    private DataManager mockDataManager() {
        MutableLiveData<List<Watchlist>> wld = new MutableLiveData<>();
        MutableLiveData<List<Stock>> sld = new MutableLiveData<>();
        DataManager dataManager = mock(DataManager.class);
        when(dataManager.getDbManager()).thenReturn(mock(DBManagerImpl.class));
        when(dataManager.getDbManager().loadWatchlists()).thenReturn(wld);
        when(dataManager.getDbManager().loadStocks(any(Watchlist.class))).thenReturn(sld);
        when(dataManager.getDbManager().deleteWatchlist(any(Watchlist.class)))
                .thenReturn(getWatchlistDeleteAction(wld));
        wld.postValue(getTestWatchList());
        sld.postValue(getTestStockList());
        return dataManager;
    }

    private Observable<Boolean> getWatchlistDeleteAction(MutableLiveData<List<Watchlist>> wld) {
        return Observable.fromCallable(() -> {
            List<Watchlist> wlist = getTestWatchList();
            wlist.remove(0);
            wld.postValue(wlist);
            return true;
        });
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
