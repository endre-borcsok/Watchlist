package com.ebsoft.watchlist.data.model.IEX

import com.ebsoft.watchlist.data.model.StockInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Symbol (

    @SerializedName("symbol")
    @Expose
    override val symbol: String = "",

    @SerializedName("securityName")
    @Expose
    override val name: String = "",

    @SerializedName("securityType")
    @Expose
    val type: String = "") : StockInfo {

    override val price: Float = 0.0f

    override val change: Float = 0.0f

    override val changePercent: Float = 0.0f
}