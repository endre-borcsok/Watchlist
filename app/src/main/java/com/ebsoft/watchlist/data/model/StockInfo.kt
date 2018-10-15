package com.ebsoft.watchlist.data.model

interface StockInfo {

    val symbol: String

    val price: Float

    val change: Float

    val changePercent: Float
}
