package com.ebsoft.watchlist.ui.mainlist;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.ListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainlistModule {

    @Provides
    MainlistViewModel provideMainViewModel(DataManager DataManager) {
        return new MainlistViewModel(DataManager);
    }

    @Provides
    ListAdapter providesAdapter() {
        return new ListAdapter<Watchlist>(R.layout.layout_main_menu_cardview_item);
    }
}
