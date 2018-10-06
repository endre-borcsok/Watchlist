package com.ebsoft.watchlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
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

import static org.hamcrest.CoreMatchers.equalTo;
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

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDataBase = Room.inMemoryDatabaseBuilder(context, AbstractDataBase.class)
                .allowMainThreadQueries()
                .build();
        mStockDao = mDataBase.stockDao();
    }

    @After
    public void closeDb() throws IOException {
        mDataBase.close();
    }

    @Test
    public void testStockDaoMethods() throws Exception {
        Stock stock = new Stock();
        stock.setSymbol(SYMBOL);
        stock.setId(STOCK_ID);
        stock.setListid(WLIST_ID);

        mStockDao.insert(stock);
        List<Stock> byName = mStockDao.findBySymbol(SYMBOL);
        assertTrue(byName.get(0).getSymbol().equals(SYMBOL));

        stock.setSymbol(SYMBOL_0);
        mStockDao.update(stock);
        List<Stock> byNameUpdate = mStockDao.findBySymbol(SYMBOL_0);
        assertTrue(byNameUpdate.get(0).getSymbol().equals(SYMBOL_0));

        List<Stock> all = mStockDao.loadAll();
        assertTrue(all.size() == 1);

        List<Stock> byId = LiveDataTestUtil.getValue(mStockDao.findByWatchlist(WLIST_ID));
        assertTrue(byId.get(0).getListid() == WLIST_ID);

        mStockDao.delete(stock);
        List<Stock> afterDelete = mStockDao.loadAll();
        assertTrue(afterDelete.size() == 0);
    }
}