package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.apache.commons.lang3.mutable.MutableInt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 09/09/18.
 */

@Entity(tableName = "watchlist")
public class Watchlist implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    private final String name;

    @Ignore
    private final MutableInt mStockCount;

    public Watchlist(String name) {
        this.name = name;
        this.mStockCount = new MutableInt();
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockCount() {
        return mStockCount.intValue();
    }

    public void setStockCount(int count) {
        this.mStockCount.setValue(count);
    }
}
