package com.ebsoft.watchlist.di

import com.ebsoft.watchlist.ui.main.MainActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by endre on 07/09/18.
 */

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
}
