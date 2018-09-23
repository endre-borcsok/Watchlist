package com.ebsoft.watchlist.data.model.Yahoo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import retrofit2.Response;

/**
 * Created by endre on 09/09/18.
 */

public class SymbolSearch {

    private final static String SYMBOL_FILTER = "S";

    @SerializedName("ResultSet")
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

    public static void processResponse(Response<SymbolSearch> response, List<String> list) {
        List<Item> items = response.body()
                .getSymbolSearchResponse()
                .getItems();
        list.clear();
        for (Item item : items) {
            if (item.getType().equals(SYMBOL_FILTER)) {
                list.add(item.getSymbol());
            }
        }
    }
}
