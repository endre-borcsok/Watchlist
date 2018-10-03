package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ebsoft.watchlist.data.model.StockInfo;
import com.ebsoft.watchlist.data.model.yahoo.Item;

import java.io.Serializable;

/**
 * Created by endre on 23/09/18.
 */

@Entity(tableName = "stock")
public class Stock implements Serializable, StockInfo {

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    @NonNull
    private String symbol = "";

    @ColumnInfo(name = "listid")
    @NonNull
    private String listid = "";

    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "change")
    private float change;

    @ColumnInfo(name = "changePercent")
    private float changePercent;

    @Override
    @NonNull
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public float getPrice() {
        return price;
    }

    @Override
    public float getChange() {
        return change;
    }

    @Override
    public float getChangePercent() {
        return changePercent;
    }

    @NonNull
    public String getListid() {
        return listid;
    }

    public void setListid(String listId) {
        this.listid = listId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public void setChangePercent(float changePercent) {
        this.changePercent = changePercent;
    }

    public void update(StockInfo stockInfo) {
        this.setPrice(stockInfo.getPrice());
        this.setChange(stockInfo.getChange());
        this.setChangePercent(stockInfo.getChangePercent());
    }

    public static Stock create(Item item, Watchlist watchlist) {
        Stock stock = new Stock();
        stock.setSymbol(item.getSymbol());
        stock.setListid(watchlist.getName());
        return stock;
    }
}
