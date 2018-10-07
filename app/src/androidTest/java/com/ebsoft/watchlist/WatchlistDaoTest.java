package com.ebsoft.watchlist;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.local.db.AbstractDataBase;
import com.ebsoft.watchlist.data.local.db.dao.WatchlistDao;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by endre on 06/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class WatchlistDaoTest {

    private final String WLIST_NAME = "AAA";
    private final int WLIST_ID = 1;

    private WatchlistDao mWatchlistDao;

    private AbstractDataBase mDataBase;

    private Watchlist mWatchlist;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        mDataBase = DbManagerUtil.getDb(InstrumentationRegistry.getTargetContext());
        mWatchlistDao = mDataBase.watchlistDao();
        mWatchlist = new Watchlist(WLIST_NAME);
        mWatchlist.setId(WLIST_ID);
    }

    @After
    public void closeDb() throws IOException {
        mDataBase.close();
        mWatchlist = null;
        mWatchlistDao = null;
        mDataBase = null;
    }

    @Test
    public void testWatchlistDaoMethods() throws Exception {
        assertTrue(insertAndFindById().getId() == WLIST_ID);
        assertTrue(loadAll().size() == 1);
        assertTrue(deleteAndLoadAll().size() == 0);
    }

    private Watchlist insertAndFindById() {
        mWatchlistDao.insert(mWatchlist);
        return mWatchlistDao.findById(WLIST_ID);
    }

    private List<Watchlist> loadAll() throws InterruptedException {
         return LiveDataTestUtil.getValue(mWatchlistDao.loadAll());
    }

    private List<Watchlist> deleteAndLoadAll() throws InterruptedException {
        mWatchlistDao.delete(mWatchlist);
        return LiveDataTestUtil.getValue(mWatchlistDao.loadAll());
    }
}
