package com.ebsoft.watchlist.ui.search;

import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.di.SearchActivityQualifier;
import com.ebsoft.watchlist.ui.adapter.CardViewAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 09/09/18.
 */

@Module
public class SearchActivityModule {

    @Provides
    SearchViewModel provideSearchViewModel(DataManager DataManager) {
        return new SearchViewModel(DataManager);
    }

    @Provides
    @SearchActivityQualifier
    LinearLayoutManager providesLayoutManager(SearchActivity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    CardViewAdapter providesAdapter() {
        return new CardViewAdapter<>(R.layout.layout_search_item,
                SearchViewHolder.class);
    }
}
