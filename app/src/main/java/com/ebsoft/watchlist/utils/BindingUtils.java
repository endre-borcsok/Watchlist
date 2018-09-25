package com.ebsoft.watchlist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.base.BindableAdapter;
import com.ebsoft.watchlist.ui.main.WatchlistAdapter;
import com.ebsoft.watchlist.ui.search.SearchAdapter;
import com.ebsoft.watchlist.ui.watchlist.StockAdapter;

import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class BindingUtils {

    @BindingAdapter({"data"})
    public static <T> void bindData(RecyclerView recyclerView, T data) {
        if (recyclerView.getAdapter() instanceof BindableAdapter) {
            ((BindableAdapter) recyclerView.getAdapter()).clearItems();
            ((BindableAdapter) recyclerView.getAdapter()).addItems(data);
        }
    }
}
