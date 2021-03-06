package com.ebsoft.watchlist.data.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.databinding.ObservableInt
import java.io.Serializable

/**
 * Created by endre on 09/09/18.
 */

@Entity(tableName = "watchlist")
data class Watchlist @Ignore constructor (
        @ColumnInfo(name = "name")
        var name: String = "",

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @Ignore
        val stockCountObservable: ObservableInt = ObservableInt(0)) : Serializable {

        constructor(name: String) : this(name, 0, ObservableInt(0))
}
