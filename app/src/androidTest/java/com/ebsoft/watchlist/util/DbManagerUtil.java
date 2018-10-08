package com.ebsoft.watchlist.util;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.ebsoft.watchlist.data.local.db.AbstractDataBase;

/**
 * Created by endre on 07/10/18.
 */

public class DbManagerUtil {

    public static AbstractDataBase getDb(Context c) {
        return Room.inMemoryDatabaseBuilder(c, AbstractDataBase.class)
                .allowMainThreadQueries()
                .build();
    }
}
