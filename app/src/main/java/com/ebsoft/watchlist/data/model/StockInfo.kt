package com.ebsoft.watchlist.data.model

import java.io.Serializable

interface StockInfo : Serializable {

    val symbol: String

    val name: String

    val price: Float

    val change: Float

    val changePercent: Float
}
