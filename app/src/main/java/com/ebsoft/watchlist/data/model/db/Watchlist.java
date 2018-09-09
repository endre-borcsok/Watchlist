package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by endre on 09/09/18.
 */

@Entity(tableName = "watchlist")
public class Watchlist {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    public String name;
}
