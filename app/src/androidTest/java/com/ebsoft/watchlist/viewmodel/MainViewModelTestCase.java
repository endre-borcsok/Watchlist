package com.ebsoft.watchlist.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.control.db.DBManagerImpl;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.main.MainViewModel;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by endre on 08/10/18.
 */

public class MainViewModelTestCase {
    private final MutableLiveData<List<Watchlist>> wld = new MutableLiveData<>();
    private final MutableLiveData<List<Stock>> sld = new MutableLiveData<>();
    private final MainViewModel mMainViewModel;

    public MainViewModelTestCase() {
        mMainViewModel = new MainViewModel(mockDataManager(wld, sld));
    }

    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    public MutableLiveData<List<Watchlist>> getWatchlistLiveData() {
        return wld;
    }

    public MutableLiveData<List<Stock>> getStockListLiveData() {
        return sld;
    }

    private DataManager mockDataManager(MutableLiveData<List<Watchlist>> wld,
                                        MutableLiveData<List<Stock>> sld) {
        DataManager dataManager = mock(DataManager.class);
        when(dataManager.getDbManager()).thenReturn(mock(DBManagerImpl.class));
        when(dataManager.getDbManager().loadWatchlists()).thenReturn(Observable.just(wld));
        when(dataManager.getDbManager().loadStocks(any(Watchlist.class)))
                .thenReturn(Observable.just(sld));
        return dataManager;
    }
}
