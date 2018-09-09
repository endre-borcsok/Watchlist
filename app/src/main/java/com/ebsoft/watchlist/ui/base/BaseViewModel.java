package com.ebsoft.watchlist.ui.base;

import android.arch.lifecycle.ViewModel;

import com.ebsoft.watchlist.data.DataManager;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by endre on 07/09/18.
 */

public abstract class BaseViewModel extends ViewModel {

    protected final DataManager mDataManager;
    protected final CompositeDisposable mCompositeDisposable;

    public BaseViewModel(DataManager DataManager) {
        this.mDataManager = DataManager;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
