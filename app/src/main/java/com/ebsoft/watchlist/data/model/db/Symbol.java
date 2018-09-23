package com.ebsoft.watchlist.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by endre on 23/09/18.
 */

@Entity(tableName = "symbol")
public class Symbol {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    public final String name;

    @ColumnInfo(name = "listid")
    @NonNull
    public final String listid;

    public Symbol(String name, String listid) {
        this.name = name;
        this.listid = listid;
    }
}
