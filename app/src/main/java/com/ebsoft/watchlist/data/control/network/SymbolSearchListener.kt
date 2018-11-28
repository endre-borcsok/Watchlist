package com.ebsoft.watchlist.data.control.network

import com.ebsoft.watchlist.data.model.yahoo.Item

interface SymbolSearchListener {

    fun onComplete(list: List<Item>)
}
