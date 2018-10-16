package com.ebsoft.watchlist.data.model.yahoo

import com.ebsoft.watchlist.data.model.StockInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

import java.io.Serializable

/**
 * Created by endre on 09/09/18.
 */

data class Item (

    @SerializedName("symbol")
    @Expose
    override val symbol: String = "",

    @SerializedName("name")
    @Expose
    val name: String = "",

    @SerializedName("type")
    @Expose
    val type: String = "") : Serializable, StockInfo {

    override val price: Float = 0.0f

    override val change: Float = 0.0f

    override val changePercent: Float = 0.0f
}