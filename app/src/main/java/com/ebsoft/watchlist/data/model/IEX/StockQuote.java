package com.ebsoft.watchlist.data.model.IEX;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockQuote {

    @SerializedName("quote")
    @Expose
    private Quote quote;

    public Quote getQuote() {
        return quote;
    }
}
