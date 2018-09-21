package com.ebsoft.watchlist.ui.search;

import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.di.SearchActivityQualifier;

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
    SearchAdapter providesAdapter() {
        return new SearchAdapter();
    }
}
