package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by endre on 23/09/18.
 */

@Entity(tableName = "stock")
public class Stock {

    @PrimaryKey
    @ColumnInfo(name = "symbol")
    @NonNull
    public final ObservableField<String> symbol;

    @ColumnInfo(name = "listid")
    @NonNull
    public final String listid;

    @ColumnInfo(name = "price")
    public float price = 0.0f;

    @ColumnInfo(name = "change")
    public float change = 0.0f;

    @ColumnInfo(name = "changePercent")
    public float changePercent = 0.0f;

    public Stock(String symbol, String listid) {
        this.symbol = new ObservableField<>(symbol);
        this.listid = listid;
    }

    public void update(Stock updatedStock) {
        this.price = updatedStock.price;
        this.change = updatedStock.change;
        this.changePercent = updatedStock.changePercent;
    }
}
