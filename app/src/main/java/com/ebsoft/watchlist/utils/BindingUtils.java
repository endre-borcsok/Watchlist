package com.ebsoft.watchlist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.main.WatchlistAdapter;
import com.ebsoft.watchlist.ui.search.SearchAdapter;

import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class BindingUtils {

    @BindingAdapter({"adapter"})
    public static void addWatchListItems(RecyclerView recyclerView, List<Watchlist> items) {
        WatchlistAdapter adapter = (WatchlistAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(items);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addSearchListItems(RecyclerView recyclerView, List<String> items) {
        SearchAdapter adapter = (SearchAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(items);
        }
    }
}
