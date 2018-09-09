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

    private APIManager mApiManager;

    private com.ebsoft.watchlist.data.local.db.DBManager mDbManager;

    @Inject
    public DataManagerImpl(APIManager ApiManager, com.ebsoft.watchlist.data.local.db.DBManager DbManager){
        this.mApiManager = ApiManager;
        this.mDbManager = DbManager;
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
