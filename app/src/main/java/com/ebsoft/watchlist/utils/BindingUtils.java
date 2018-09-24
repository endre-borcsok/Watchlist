package com.ebsoft.watchlist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.main.WatchlistAdapter;
import com.ebsoft.watchlist.ui.search.SearchAdapter;
import com.ebsoft.watchlist.ui.watchlist.StockAdapter;

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
        SearchAdapter adapter = (SearchAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(items);
        }
    }

    @BindingAdapter({"data"})
    public static void addStockListItems(RecyclerView recyclerView, List<Stock> items) {
        StockAdapter adapter = (StockAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(items);
        }
    }
}
