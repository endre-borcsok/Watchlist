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

    public void update(Stock updatedStock) {
        this.price = updatedStock.price;
        this.change = updatedStock.change;
        this.changePercent = updatedStock.changePercent;
    }
}
