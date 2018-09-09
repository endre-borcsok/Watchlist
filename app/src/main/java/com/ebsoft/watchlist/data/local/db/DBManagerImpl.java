package com.ebsoft.watchlist.data.local.db;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by endre on 09/09/18.
 */

@Singleton
public class DBManagerImpl implements DBManager {

    private AbstractDataBase mDataBase;

    @Inject
    public DBManagerImpl(AbstractDataBase dataBase) {
        this.mDataBase = dataBase;
    }
}
