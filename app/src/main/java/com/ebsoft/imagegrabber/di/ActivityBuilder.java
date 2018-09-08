package com.ebsoft.imagegrabber.di;

import com.ebsoft.imagegrabber.ui.main.MainActivity;
import com.ebsoft.imagegrabber.ui.main.MainActivityModule;

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
}
