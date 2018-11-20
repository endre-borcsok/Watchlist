package com.ebsoft.watchlist.ui.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableList

import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.yahoo.Item
import com.ebsoft.watchlist.ui.base.BaseViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.coroutines.launch

/**
 * Created by endre on 09/09/18.
 */

class SearchViewModel(DataManager: DataManager) : BaseViewModel<Any>(DataManager) {

    private val list: ObservableList<Item> = ObservableArrayList()

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
}
