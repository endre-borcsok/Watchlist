package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.control.db.DBManager;
import com.ebsoft.watchlist.data.control.network.APIManager;

/**
 * Created by endre on 08/09/18.
 */

public interface DataManager {

    DBManager getDbManager();

    APIManager getApiManager();
}
