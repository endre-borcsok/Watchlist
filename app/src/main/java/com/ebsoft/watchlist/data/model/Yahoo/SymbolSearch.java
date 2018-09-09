package com.ebsoft.watchlist.data.model.Yahoo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by endre on 09/09/18.
 */

public class SymbolSearch {

    @SerializedName("SymbolSearchResponse")
    @Expose
    private SymbolSearchResponse symbolSearchResponse;

    public SymbolSearch(SymbolSearchResponse symbolSearchResponse) {
        super();
        this.symbolSearchResponse = symbolSearchResponse;
    }

    public SymbolSearchResponse getSymbolSearchResponse() {
        return symbolSearchResponse;
    }

    public void setSymbolSearchResponse(SymbolSearchResponse symbolSearchResponse) {
        this.symbolSearchResponse = symbolSearchResponse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbolSearchResponse", symbolSearchResponse)
                .toString();
    }

}
