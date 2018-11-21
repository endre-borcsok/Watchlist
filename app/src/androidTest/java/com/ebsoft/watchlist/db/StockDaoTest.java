package com.ebsoft.watchlist.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.control.db.AbstractDataBase;
import com.ebsoft.watchlist.data.control.db.dao.StockDao;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.util.DbManagerUtil;
import com.ebsoft.watchlist.util.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by endre on 06/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class StockDaoTest {

    private final String SYMBOL = "AAA";
    private final String SYMBOL_0 = "BBB";
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
        mStock.setId(1);
        mStock.setListid(WLIST_ID);
        mDataBase = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        mStockDao = mDataBase.stockDao();
    }

    @After
    public void closeDb() {
        mDataBase.close();
        mDataBase = null;
        mStock = null;
        mStockDao = null;
    }

    @Test
    public void testStockDaoMethods() throws Exception {
        assertEquals(insertAndFindBySymbol().get(0).getSymbol(), SYMBOL);
        assertEquals(updateAndFindBySymbol().get(0).getSymbol(), SYMBOL_0);
        assertEquals(1, loadAll().size());
        assertEquals(findByWatchlist().get(0).getListid(), WLIST_ID);
        assertEquals(0, deleteAndLoadAll().size());
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