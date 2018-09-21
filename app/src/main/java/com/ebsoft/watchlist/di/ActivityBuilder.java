package com.ebsoft.watchlist.di;

import com.ebsoft.watchlist.ui.create.CreateWatchlistActivity;
import com.ebsoft.watchlist.ui.create.CreateWatchlistModule;
import com.ebsoft.watchlist.ui.main.MainActivity;
import com.ebsoft.watchlist.ui.main.MainActivityModule;
import com.ebsoft.watchlist.ui.search.SearchActivity;
import com.ebsoft.watchlist.ui.search.SearchActivityModule;
import com.ebsoft.watchlist.ui.watchlist.WatchlistActivity;
import com.ebsoft.watchlist.ui.watchlist.WatchlistModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by endre on 07/09/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            SearchActivityModule.class})
    abstract SearchActivity bindSearchActivity();

    @ContributesAndroidInjector(modules = {
            CreateWatchlistModule.class})
    abstract CreateWatchlistActivity bindCreateWatchlistActivity();

    @ContributesAndroidInjector(modules = {
            WatchlistModule.class})
    abstract WatchlistActivity bindWatchlistActivity();
}
