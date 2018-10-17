package com.ebsoft.watchlist.ui.search;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.data.model.yahoo.Item;
import com.ebsoft.watchlist.ui.adapter.ListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 09/09/18.
 */

@Module
public class SearchModule {

    @Provides
    SearchViewModel provideSearchViewModel(DataManager DataManager) {
        return new SearchViewModel(DataManager);
    }

    @Provides
    ListAdapter providesAdapter() {
        return new ListAdapter<Item>(R.layout.layout_search_item);
    }
}
