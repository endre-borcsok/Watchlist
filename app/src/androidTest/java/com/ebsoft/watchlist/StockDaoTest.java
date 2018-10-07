package com.ebsoft.watchlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.local.db.AbstractDataBase;
import com.ebsoft.watchlist.data.local.db.dao.StockDao;
import com.ebsoft.watchlist.data.model.db.Stock;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by endre on 06/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class StockDaoTest {

    private final String SYMBOL = "AAA";
    private final String SYMBOL_0 = "BBB";
    private final int STOCK_ID = 1;
    private final int WLIST_ID = 2;

    private StockDao mStockDao;

    private AbstractDataBase mDataBase;

    private Stock mStock;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        mStock = new Stock();
        mStock.setSymbol(SYMBOL);
        mStock.setId(STOCK_ID);
        mStock.setListid(WLIST_ID);
        mDataBase = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        mStockDao = mDataBase.stockDao();
    }

    @After
    public void closeDb() throws IOException {
        mDataBase.close();
        mDataBase = null;
        mStock = null;
        mStockDao = null;
    }

    @Test
    public void testStockDaoMethods() throws Exception {
        assertTrue(insertAndFindBySymbol().get(0).getSymbol().equals(SYMBOL));
        assertTrue(updateAndFindBySymbol().get(0).getSymbol().equals(SYMBOL_0));
        assertTrue(loadAll().size() == 1);
        assertTrue(findByWatchlist().get(0).getListid() == WLIST_ID);
        assertTrue(deleteAndLoadAll().size() == 0);
    }

    private List<Stock> insertAndFindBySymbol() {
        mStockDao.insert(mStock);
        return mStockDao.findBySymbol(SYMBOL);
    }

    private List<Stock> updateAndFindBySymbol() {
        mStock.setSymbol(SYMBOL_0);
        mStockDao.update(mStock);
        return mStockDao.findBySymbol(SYMBOL_0);
    }

    private List<Stock> loadAll() {
        return mStockDao.loadAll();
    }

    private List<Stock> findByWatchlist() throws InterruptedException {
        return LiveDataTestUtil.getValue(mStockDao.findByWatchlist(WLIST_ID));
    }

    private List<Stock> deleteAndLoadAll() {
        mStockDao.delete(mStock);
        return mStockDao.loadAll();
    }
}