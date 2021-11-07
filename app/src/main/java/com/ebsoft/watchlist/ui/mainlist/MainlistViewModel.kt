package com.ebsoft.watchlist.ui.mainlist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableArrayList
import android.databinding.ObservableList

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.ui.base.BaseViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by endre on 07/09/18.
 */

class MainlistViewModel(DataManager: DataManager) : BaseViewModel<MainlistNavigator>(DataManager) {

    val list: ObservableList<Watchlist> = ObservableArrayList()

    override fun subscribeToLiveData(owner: LifecycleOwner) {
        dataManager.dbManager.loadWatchlists().observe(owner, Observer { watchlists ->
            if (watchlists != null) {
                list.clear()
                list.addAll(watchlists)
                subscribeToStockCountObserver(owner, watchlists)
            }
        })
    }

    private fun subscribeToStockCountObserver(owner: LifecycleOwner, watchlists: List<Watchlist>) {
        for (wlist in watchlists) {
            dataManager.dbManager.loadStocks(wlist).observe(owner, Observer {stocks ->
                if (stocks != null) {
                    wlist.stockCountObservable.set(stocks.size)
                }
            })
        }
    }

    fun deleteWatchlist(watchlist: Watchlist) {
        addDisposable(dataManager.dbManager
                .deleteWatchlist(watchlist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun onActionButtonClick() {
        navigator.onActionButtonClick()
    }
}
