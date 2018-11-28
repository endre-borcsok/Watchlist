package com.ebsoft.watchlist.data

import com.ebsoft.watchlist.data.control.db.DBManager
import com.ebsoft.watchlist.data.control.network.APIManager

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by endre on 08/09/18.
 */

@Singleton
class DataManagerImpl @Inject
constructor(override val apiManager: APIManager, override val dbManager: DBManager) : DataManager
