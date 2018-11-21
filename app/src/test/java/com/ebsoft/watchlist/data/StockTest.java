package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by endre on 06/10/18.
 */

public class StockTest {

    private final String SYMBOL = "AAA";

    private Stock mTestStock;

    @Before
    public void createTestStock() {
        float val = 1.0f;
        mTestStock = new Stock();
        mTestStock.setPrice(val);
        mTestStock.setChangePercent(val);
        mTestStock.setChange(val);
        mTestStock.setSymbol(SYMBOL);
    }

    @Test
    public void testStockCreation() {
        Watchlist wlist = new Watchlist("test");
        Stock newStock = Stock.Companion.create(mTestStock, wlist);
        assertNotNull(newStock);
        assertEquals(newStock.getSymbol(), SYMBOL);
        assertEquals(newStock.getListid(), wlist.getId());
    }

    @Test
    public void testStockUpadte() {
        Stock newStock = new Stock();
        newStock.update(mTestStock);
        assertEquals(newStock.getSymbol(), mTestStock.getSymbol());
        assertEquals(newStock.getChangePercent(), mTestStock.getChangePercent(), 0.0);
        assertEquals(newStock.getChange(), mTestStock.getChange(), 0.0);
        assertEquals(newStock.getPrice(), mTestStock.getPrice(), 0.0);
    }
}
