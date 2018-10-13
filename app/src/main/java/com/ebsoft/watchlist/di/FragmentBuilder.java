package com.ebsoft.watchlist.di;

import com.ebsoft.watchlist.ui.watchlists.WatchlistsFragment;
import com.ebsoft.watchlist.ui.watchlists.WatchlistsFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = WatchlistsFragmentModule.class)
    abstract WatchlistsFragment provideWatchlistFragmentFactory();
}
