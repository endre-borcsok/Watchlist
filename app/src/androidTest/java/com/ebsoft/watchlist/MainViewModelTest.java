package com.ebsoft.watchlist;

import android.support.test.runner.AndroidJUnit4;

import com.ebsoft.watchlist.ui.main.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Created by endre on 07/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class MainViewModelTest {

    private MainViewModel mMainViewModel;

    @Before
    public void setup() {
        mMainViewModel = new MainViewModel(DataManagerUtil.create());
    }

    @After
    public void release() {
        mMainViewModel = null;
    }

    @Test
    public void testListNotNull() {
        assertTrue(mMainViewModel.getList() != null);
    }
}
