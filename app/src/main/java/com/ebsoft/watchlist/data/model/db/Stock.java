package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ebsoft.watchlist.data.model.yahoo.Item;

import java.io.Serializable;

/**
 * Created by endre on 23/09/18.
 */

@Entity(tableName = "stock")
public class Stock implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    @NonNull
    private String symbol;

    @ColumnInfo(name = "listid")
    @NonNull
    private String listid;

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "change")
    private float change;

    @ColumnInfo(name = "changePercent")
    private float changePercent;

    public Stock() {}

    @NonNull
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @NonNull
    public String getListid() {
        return listid;
    }

    public void setListid(String listId) {
        this.listid = listId;
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

    public static Stock create(Item item, Watchlist watchlist) {
        Stock stock = new Stock();
        stock.setSymbol(item.getSymbol());
        stock.setListid(watchlist.getName());
        return stock;
    }
}
