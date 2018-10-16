package com.ebsoft.watchlist.data.model.yahoo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

/**
 * Created by endre on 09/09/18.
 */

data class SymbolSearch (
        @SerializedName("ResultSet")
        @Expose
        val symbolSearchResponse: SymbolSearchResponse)
