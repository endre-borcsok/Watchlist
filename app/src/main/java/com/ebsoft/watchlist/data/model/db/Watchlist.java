package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 09/09/18.
 */

@Entity(tableName = "watchlist")
public class Watchlist implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    private final String name;

    @Ignore
    private final List<Stock> mStocks;

    public Watchlist(String name) {
        this.name = name;
        this.mStocks = new ArrayList<>();
    }

    @NonNull
    public String getName() {
        return name;
    }

    public List<Stock> getStocks() {
        return mStocks;
    }
}
