package com.ebsoft.watchlist.di;

import com.ebsoft.watchlist.ui.create.CreateWatchlistFragment;
import com.ebsoft.watchlist.ui.create.CreateWatchlistModule;
import com.ebsoft.watchlist.ui.search.SearchFragment;
import com.ebsoft.watchlist.ui.search.SearchModule;
import com.ebsoft.watchlist.ui.watchlist.WatchlistFragment;
import com.ebsoft.watchlist.ui.watchlist.WatchlistModule;
import com.ebsoft.watchlist.ui.mainlist.MainlistFragment;
import com.ebsoft.watchlist.ui.mainlist.MainlistModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = MainlistModule.class)
    abstract MainlistFragment bindWatchlistsFragment();

    @ContributesAndroidInjector(modules = CreateWatchlistModule.class)
    abstract CreateWatchlistFragment bindCreateWatchlistFragment();

    @ContributesAndroidInjector(modules = {WatchlistModule.class})
    abstract WatchlistFragment bindWatchlistFragment();

    @ContributesAndroidInjector(modules = {
            SearchModule.class})
    abstract SearchFragment bindSearchFragment();
}
