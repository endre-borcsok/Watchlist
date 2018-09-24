package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by endre on 23/09/18.
 */

@Entity(tableName = "stock")
public class Stock {

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    @NonNull
    public final String symbol;

    @ColumnInfo(name = "listid")
    @NonNull
    public final String listid;

    @ColumnInfo(name = "price")
    public float price;

    @ColumnInfo(name = "change")
    public float change;

    @ColumnInfo(name = "changePercent")
    public float changePercent;

    public Stock(String symbol, String listid) {
        this.symbol = symbol;
        this.listid = listid;
    }

    @NonNull
    public String getSymbol() {
        return symbol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public float getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(float changePercent) {
        this.changePercent = changePercent;
    }
}
