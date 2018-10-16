package com.ebsoft.watchlist.data.model.AlphaVantage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AVQuote (

    @SerializedName("Global Quote")
    @Expose
    var globalQuote: GlobalQuote = GlobalQuote())