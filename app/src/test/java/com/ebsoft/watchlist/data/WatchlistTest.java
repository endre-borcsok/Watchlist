package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.model.db.Watchlist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by endre on 06/10/18.
 */

public class WatchlistTest {

    @Test
    public void testListCount() {
        int count = 5;
        Watchlist wlist = new Watchlist("test");
        wlist.getStockCountObservable().set(count);
        assertEquals(count, wlist.getStockCountObservable().get());
    }
}
