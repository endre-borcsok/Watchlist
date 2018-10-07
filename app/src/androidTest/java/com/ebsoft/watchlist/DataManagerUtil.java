package com.ebsoft.watchlist;

import android.support.test.InstrumentationRegistry;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.DataManagerImpl;
import com.ebsoft.watchlist.data.local.db.AbstractDataBase;
import com.ebsoft.watchlist.data.local.db.DBManagerImpl;

import org.junit.After;
import org.junit.Before;

/**
 * Created by endre on 07/10/18.
 */

public class DataManagerUtil {

    public static DataManager create() {
        AbstractDataBase db = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        return new DataManagerImpl(ApiManagerUtil.getMock(), new DBManagerImpl(db));
    }
}
