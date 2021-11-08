package com.ebsoft.watchlist.data.model.AlphaVantage

import com.ebsoft.watchlist.data.model.StockInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GlobalQuote (

    @SerializedName("01. symbol")
    @Expose
    override val symbol: String = "",

    @SerializedName("05. price")
    @Expose
    private val _price: String = "0.0",

    @SerializedName("09. change")
    @Expose
    private val _change: String = "0.0",

    @SerializedName("10. change percent")
    @Expose
    private val _changePercent: String = ""
) : StockInfo {

    override val price: Float
        get() = _price.toFloat()

    override val change: Float
        get() = _change.toFloat()

    override val changePercent: Float
        get() = _changePercent.toFloat()

    override var name: String = ""
}
