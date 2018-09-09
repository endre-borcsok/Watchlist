package com.ebsoft.watchlist.ui.search;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.ebsoft.watchlist.ViewModelProviderFactory;
import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.ui.main.MainActivity;
import com.ebsoft.watchlist.ui.main.MainViewModel;
import com.ebsoft.watchlist.ui.main.WatchlistAdapter;
import com.ebsoft.watchlist.utils.Constants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by endre on 09/09/18.
 */

@Module
public class SearchActivityModule {

    @Provides
    ViewModelProvider.Factory searchViewModelProvider(SearchViewModel searchViewModel) {
        return new ViewModelProviderFactory<>(searchViewModel);
    }

    @Provides
    SearchViewModel provideSearchViewModel(DataManager DataManager) {
        return new SearchViewModel(DataManager);
    }

    @Provides
    @Named("SearchActivity")
    LinearLayoutManager providesLayoutManager(SearchActivity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Provides
    @Named("SearchActivity")
    WatchlistAdapter providesAdapter() {
        return new WatchlistAdapter();
    }
}
