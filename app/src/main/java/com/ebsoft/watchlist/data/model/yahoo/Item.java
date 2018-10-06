package com.ebsoft.watchlist.data.model.yahoo;

import com.ebsoft.watchlist.data.model.StockInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by endre on 09/09/18.
 */

public class Item implements Serializable, StockInfo {

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("type")
    @Expose
    private String type;

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public float getPrice() {
        return 0;
    }

    @Override
    public float getChange() {
        return 0;
    }

    @Override
    public float getChangePercent() {
        return 0;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("name", name)
                .append("type", type)
                .toString();
    }

}