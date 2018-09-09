package com.ebsoft.watchlist.data.model.Yahoo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by endre on 09/09/18.
 */

public class Item {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("exch")
    @Expose
    private String exch;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("exchDisp")
    @Expose
    private String exchDisp;
    @SerializedName("typeDisp")
    @Expose
    private String typeDisp;

    public Item(String symbol, String name, String exch, String type, String exchDisp, String typeDisp) {
        super();
        this.symbol = symbol;
        this.name = name;
        this.exch = exch;
        this.type = type;
        this.exchDisp = exchDisp;
        this.typeDisp = typeDisp;
    }

    public String getSymbol() {
        return symbol;
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

    public String getExch() {
        return exch;
    }

    public void setExch(String exch) {
        this.exch = exch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExchDisp() {
        return exchDisp;
    }

    public void setExchDisp(String exchDisp) {
        this.exchDisp = exchDisp;
    }

    public String getTypeDisp() {
        return typeDisp;
    }

    public void setTypeDisp(String typeDisp) {
        this.typeDisp = typeDisp;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("name", name)
                .append("exch", exch)
                .append("type", type)
                .append("exchDisp", exchDisp)
                .append("typeDisp", typeDisp)
                .toString();
    }

}