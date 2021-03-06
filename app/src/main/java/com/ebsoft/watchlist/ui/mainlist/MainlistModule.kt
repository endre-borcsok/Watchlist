package com.ebsoft.watchlist.ui.mainlist

import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.ui.adapter.ListAdapter

import dagger.Module
import dagger.Provides

@Module
class MainlistModule {

    @Provides
    internal fun provideMainViewModel(DataManager: DataManager): MainlistViewModel {
        return MainlistViewModel(DataManager)
    }

    @Provides
    internal fun providesAdapter(): ListAdapter<Watchlist> {
        return ListAdapter(R.layout.layout_main_menu_cardview_item)
    }
}
