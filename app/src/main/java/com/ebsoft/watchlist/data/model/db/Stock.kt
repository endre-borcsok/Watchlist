package com.ebsoft.watchlist.data.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.ebsoft.watchlist.data.model.StockInfo

import java.io.Serializable

/**
 * Created by endre on 23/09/18.
 */

@Entity(tableName = "stock")
data class Stock (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "symbol")
        override var symbol: String = "",

        @ColumnInfo(name = "listid")
        var listid: Int = 0,

        @ColumnInfo(name = "price")
        override var price: Float = 0.toFloat(),

        @ColumnInfo(name = "change")
        override var change: Float = 0.toFloat(),

        @ColumnInfo(name = "changePercent")
        override var changePercent: Float = 0.toFloat()) : StockInfo {

    fun update(stockInfo: StockInfo) {
        this.symbol = stockInfo.symbol
        this.price = stockInfo.price
        this.change = stockInfo.change
        this.changePercent = stockInfo.changePercent
    }

    companion object {
        fun create(item: StockInfo, watchlist: Watchlist): Stock {
            val stock = Stock()
            stock.symbol = item.symbol
            stock.listid = watchlist.id
            return stock
        }
    }

    override var name: String = ""
}
