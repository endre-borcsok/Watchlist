package com.ebsoft.watchlist.ui.watchlist;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.ebsoft.watchlist.BR;

public class StockViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public StockViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
        binding.setVariable(BR.item, obj);
        binding.executePendingBindings();
    }
}
