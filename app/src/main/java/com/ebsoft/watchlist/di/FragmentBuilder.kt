package com.ebsoft.watchlist.di

import com.ebsoft.watchlist.ui.create.CreateWatchlistFragment
import com.ebsoft.watchlist.ui.create.CreateWatchlistModule
import com.ebsoft.watchlist.ui.mainlist.MainlistFragment
import com.ebsoft.watchlist.ui.mainlist.MainlistModule
import com.ebsoft.watchlist.ui.search.SearchFragment
import com.ebsoft.watchlist.ui.search.SearchModule
import com.ebsoft.watchlist.ui.watchlist.WatchlistFragment
import com.ebsoft.watchlist.ui.watchlist.WatchlistModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainlistModule::class))
    internal abstract fun bindWatchlistsFragment(): MainlistFragment

    @ContributesAndroidInjector(modules = arrayOf(CreateWatchlistModule::class))
    internal abstract fun bindCreateWatchlistFragment(): CreateWatchlistFragment

    @ContributesAndroidInjector(modules = arrayOf(WatchlistModule::class))
    internal abstract fun bindWatchlistFragment(): WatchlistFragment

    @ContributesAndroidInjector(modules = arrayOf(SearchModule::class))
    internal abstract fun bindSearchFragment(): SearchFragment
}
