package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.local.db.DBManager;
import com.ebsoft.watchlist.network.APIManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by endre on 08/09/18.
 */

@Singleton
public class DataManagerImpl implements DataManager {

    private final APIManager mApiManager;

    private final DBManager mDbManager;

    @Inject
    public DataManagerImpl(APIManager apiManger, DBManager dbManager) {
        mApiManager = apiManger;
        mDbManager = dbManager;
    }

    @Override
    public APIManager getApiManager() {
        return mApiManager;
    }

    @Override
    public DBManager getDbManager() {
        return mDbManager;
    }
}
