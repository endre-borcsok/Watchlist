package com.ebsoft.watchlist.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.data.control.db.AbstractDataBase;
import com.ebsoft.watchlist.data.control.db.dao.WatchlistDao;
import com.ebsoft.watchlist.data.model.db.Watchlist;
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
public class WatchlistDaoTest {

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
        mWatchlist = new Watchlist("AAA");
        mWatchlist.setId(WLIST_ID);
    }

    @After
    public void closeDb() {
        mDataBase.close();
        mWatchlist = null;
        mWatchlistDao = null;
        mDataBase = null;
    }

    @Test
    public void testWatchlistDaoMethods() throws Exception {
        assertEquals(insertAndFindById().getId(), WLIST_ID);
        assertEquals(1, loadAll().size());
        assertEquals(0, deleteAndLoadAll().size());
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
