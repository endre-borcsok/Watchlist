package com.ebsoft.watchlist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.ui.main.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class BindingUtils {
    @BindingAdapter({"adapter"})
    public static void addListItems(RecyclerView recyclerView, List<String> items) {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(items);
        }
    }
}
