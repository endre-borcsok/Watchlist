package com.ebsoft.watchlist.ui.watchlist

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.ui.base.BaseViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * Created by endre on 21/09/18.
 */

class WatchlistViewModel(DataManager: DataManager, val watchlist: Watchlist) : BaseViewModel<WatchlistNavigator>(DataManager) {

    val list: ObservableList<Stock> = ObservableArrayList()

    val loading = ObservableBoolean()

    private var mRequestRefresh = false

    fun subscribeToLiveData(owner: LifecycleOwner) {
        mRequestRefresh = true
        dataManager.dbManager.loadStocks(watchlist).observe(owner, Observer<List<Stock>> { stocks ->
            list.clear()
            list.addAll(stocks!!)
            if (mRequestRefresh) {
                mRequestRefresh = false
                refresh()
            }
        })
    }

    fun removeStock(stock: Stock) {
        addDisposable(dataManager.dbManager
                .deleteStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun refresh() {
        if (list.size < 1) return
        loading.set(true)
        launch(coroutineContext) {
            dataManager.apiManager.getBatchQuote(list)
            for (stock in list) {
                updateStock(stock)
            }
            loading.set(false)
        }
    }

    private fun updateStock(stock: Stock) {
        addDisposable(dataManager.dbManager
                .updateStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun onActionButtonClick() {
        navigator.onActionButtonClick()
    }
}
