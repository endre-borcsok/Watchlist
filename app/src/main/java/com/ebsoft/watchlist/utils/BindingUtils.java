package com.ebsoft.watchlist.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.ui.adapter.BindableAdapter;

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
