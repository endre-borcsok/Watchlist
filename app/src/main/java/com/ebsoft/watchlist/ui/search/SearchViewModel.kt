package com.ebsoft.watchlist.ui.search

import android.arch.lifecycle.LifecycleOwner
import android.databinding.ObservableArrayList
import android.databinding.ObservableList

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.StockInfo
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.yahoo.Item
import com.ebsoft.watchlist.ui.base.BaseNavigator
import com.ebsoft.watchlist.ui.base.BaseViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.coroutines.launch

/**
 * Created by endre on 09/09/18.
 */

class SearchViewModel(DataManager: DataManager) : BaseViewModel<BaseNavigator>(DataManager) {

    val list: ObservableList<StockInfo> = ObservableArrayList()

    fun performSearch(symbol: String) {
        launch(coroutineContext) {
            list.clear()
            list.addAll(dataManager.apiManager.searchSymbol(symbol))
        }
    }

    fun insertStock(stock: Stock) {
        addDisposable(dataManager.dbManager
                .insertStock(stock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    override fun subscribeToLiveData(owner: LifecycleOwner) {}
}
