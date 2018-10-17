package com.ebsoft.watchlist.data.model.IEX

import com.ebsoft.watchlist.data.model.StockInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quote (

    @SerializedName("symbol")
    @Expose
    override val symbol: String = "",

    @SerializedName("latestPrice")
    @Expose
    override val price: Float = 0.0f,

    @SerializedName("change")
    @Expose
    override val change: Float = 0.0f,

    @SerializedName("changePercent")
    @Expose
    override val changePercent: Float = 0.0f) : StockInfo