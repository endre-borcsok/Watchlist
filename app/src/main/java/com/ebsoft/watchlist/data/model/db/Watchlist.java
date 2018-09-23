package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by endre on 09/09/18.
 */

@Entity(tableName = "watchlist")
public class Watchlist implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    public final String name;

    public Watchlist(String name) {
        this.name = name;
    }
}
