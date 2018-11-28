package com.ebsoft.watchlist.ui.adapter

interface BindableAdapter<T> {

    fun addItems(items: T)

    fun clearItems()
}
