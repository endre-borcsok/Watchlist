package com.ebsoft.watchlist.ui.base;

import android.arch.lifecycle.ViewModel;

import com.ebsoft.watchlist.data.DataManager;

/**
 * Created by endre on 07/09/18.
 */

public abstract class BaseViewModel extends ViewModel {

    private final DataManager mDataManager;

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager = dataManager;
    }
}
