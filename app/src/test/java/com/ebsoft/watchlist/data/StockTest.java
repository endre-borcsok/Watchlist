package com.ebsoft.watchlist.data;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by endre on 06/10/18.
 */

public class StockTest {

    private final String SYMBOL = "AAA";

    private final float VALUE = 1.0f;

    private Stock mTestStock;

    @Before
    public void createTestStock() {
        mTestStock = new Stock();
        mTestStock.setPrice(VALUE);
        mTestStock.setChangePercent(VALUE);
        mTestStock.setChange(VALUE);
        mTestStock.setSymbol(SYMBOL);
    }

    @Test
    public void testStockCreation() {
        Watchlist wlist = new Watchlist("test");
        Stock newStock = Stock.create(mTestStock, wlist);
        assertTrue(newStock != null);
        assertTrue(newStock.getSymbol().equals(SYMBOL));
        assertTrue(newStock.getListid() == wlist.getId());
    }

    @Test
    public void testStockUpadte() {
        Stock newStock = new Stock();
        newStock.update(mTestStock);
        assertTrue(newStock.getSymbol().equals(mTestStock.getSymbol()));
        assertTrue(newStock.getChangePercent() == mTestStock.getChangePercent());
        assertTrue(newStock.getChange() == mTestStock.getChange());
        assertTrue(newStock.getPrice() == mTestStock.getPrice());
    }
}
