package com.ebsoft.watchlist.ui.create

import android.databinding.ObservableField

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.ui.base.BaseViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by endre on 21/09/18.
 */

class CreateWatchlistViewModel(DataManager: DataManager) : BaseViewModel<CreateWatchlistNavigator>(DataManager) {

    val editTextContent = ObservableField("")

    fun create() {
        val watchlist = Watchlist(editTextContent.get()!!)
        addDisposable(dataManager.dbManager.saveWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> navigator.onWatchlistCreated() })
    }
}
