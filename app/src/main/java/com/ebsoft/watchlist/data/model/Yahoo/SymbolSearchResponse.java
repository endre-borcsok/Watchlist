package com.ebsoft.watchlist.data.model.Yahoo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import retrofit2.Response;

/**
 * Created by endre on 09/09/18.
 */

public class SymbolSearchResponse {

    private final static String SYMBOL_FILTER = "S";

    @SerializedName("Query")
    @Expose
    private String query;
    @SerializedName("Result")
    @Expose
    private List<Item> items = null;

    public SymbolSearchResponse(String query, List<Item> items) {
        super();
        this.query = query;
        this.items = items;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("query", query)
                .append("items", items)
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