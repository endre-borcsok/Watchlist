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

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDataBase = Room.inMemoryDatabaseBuilder(context, AbstractDataBase.class)
                .allowMainThreadQueries()
                .build();
        mWatchlistDao = mDataBase.watchlistDao();
    }

    @After
    public void closeDb() throws IOException {
        mDataBase.close();
    }

    @Test
    public void testWatchlistDaoMethods() throws Exception {
        Watchlist wlist = new Watchlist(WLIST_NAME);
        wlist.setId(WLIST_ID);

        mWatchlistDao.insert(wlist);
        Watchlist findById = mWatchlistDao.findById(WLIST_ID);
        assertTrue(findById.getId() == WLIST_ID);

        List<Watchlist> all = LiveDataTestUtil.getValue(mWatchlistDao.loadAll());
        assertTrue(all.size() == 1);

        mWatchlistDao.delete(wlist);
        List<Watchlist> delete = LiveDataTestUtil.getValue(mWatchlistDao.loadAll());
        assertTrue(delete.size() == 0);
    }
}
