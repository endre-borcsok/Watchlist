package com.ebsoft.watchlist.di;

import com.ebsoft.watchlist.ui.main.MainActivity;
import com.ebsoft.watchlist.ui.search.SearchActivity;
import com.ebsoft.watchlist.ui.search.SearchActivityModule;
import com.ebsoft.watchlist.ui.watchlist.WatchlistFragment;
import com.ebsoft.watchlist.ui.watchlist.WatchlistModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by endre on 07/09/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            SearchActivityModule.class})
    abstract SearchActivity bindSearchActivity();
}
