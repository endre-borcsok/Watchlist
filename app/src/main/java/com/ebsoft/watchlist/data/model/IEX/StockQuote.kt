package com.ebsoft.watchlist.data.model.IEX

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StockQuote (
        
    @SerializedName("quote")
    @Expose
    val quote: Quote = Quote())
