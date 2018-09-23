package com.ebsoft.watchlist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.main.WatchlistAdapter;
import com.ebsoft.watchlist.ui.search.SearchAdapter;
import com.ebsoft.watchlist.ui.watchlist.SymbolAdapter;

import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class BindingUtils {

    @BindingAdapter({"data"})
    public static void addWatchListItems(RecyclerView recyclerView, List<Watchlist> items) {
        WatchlistAdapter adapter = (WatchlistAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(items);
        }
    }

    @BindingAdapter({"data"})
    public static void addStringListItems(RecyclerView recyclerView, List<String> items) {
        if (recyclerView.getAdapter() instanceof SearchAdapter) {
            SearchAdapter adapter = (SearchAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(items);
            }
        } else if (recyclerView.getAdapter() instanceof SymbolAdapter) {
            SymbolAdapter adapter = (SymbolAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(items);
            }
        }
    }
}
