package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.model.db.Watchlist;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by endre on 06/10/18.
 */

public class WatchlistTest {

    @Test
    public void testListCount() {
        Watchlist wlist = new Watchlist("test");
        int count = 5;
        wlist.getStockCountObservable().set(count);
        assertTrue(wlist.getStockCountObservable().get() == count);
    }
}
