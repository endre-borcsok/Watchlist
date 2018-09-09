package com.ebsoft.watchlist.ui.base;

import android.arch.lifecycle.ViewModel;

import com.ebsoft.watchlist.data.DataManager;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by endre on 07/09/18.
 */

public abstract class BaseViewModel<N> extends ViewModel {

    private WeakReference<N> mNavigator;
    protected final DataManager mDataManager;
    protected final CompositeDisposable mCompositeDisposable;

    public BaseViewModel(DataManager DataManager) {
        this.mDataManager = DataManager;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
