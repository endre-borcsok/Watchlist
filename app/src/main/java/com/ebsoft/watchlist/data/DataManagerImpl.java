package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.control.db.DBManager;
import com.ebsoft.watchlist.data.control.network.APIManager;
import com.ebsoft.watchlist.data.control.nonpersistent.VolatileStorage;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by endre on 08/09/18.
 */

@Singleton
public class DataManagerImpl implements DataManager {

    private final APIManager mApiManager;

    private final DBManager mDbManager;

    private final VolatileStorage mVolatileStorage;

    @Inject
    public DataManagerImpl(APIManager apiManger, DBManager dbManager, VolatileStorage volatileStorage) {
        mApiManager = apiManger;
        mDbManager = dbManager;
        mVolatileStorage = volatileStorage;
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
