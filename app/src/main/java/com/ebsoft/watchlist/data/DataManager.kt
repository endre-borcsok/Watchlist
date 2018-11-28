package com.ebsoft.watchlist.data

import com.ebsoft.watchlist.data.control.db.DBManager
import com.ebsoft.watchlist.data.control.network.APIManager

/**
 * Created by endre on 08/09/18.
 */

interface DataManager {

    val dbManager: DBManager

    val apiManager: APIManager
}
