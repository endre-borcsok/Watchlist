package com.ebsoft.watchlist.ui.create;

import android.databinding.ObservableField;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by endre on 21/09/18.
 */

public class CreateWatchlistViewModel extends BaseViewModel<CreateWatchlistNavigator> {

    private ObservableField<String> editTextContent = new ObservableField<>("");

    public CreateWatchlistViewModel(DataManager DataManager) {
        super(DataManager);
    }

    public ObservableField<String> getEditTextContent() {
        return editTextContent;
    }

    public void create() {
        Watchlist watchlist = new Watchlist(editTextContent.get());
        getCompositeDisposable().add(
                mDataManager.getDbManager().saveWatchlist(watchlist)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(aBoolean -> getNavigator().onWatchlistCreated()));
    }
}
