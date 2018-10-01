package com.ebsoft.watchlist.ui.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ebsoft.watchlist.BR;
import com.ebsoft.watchlist.R;

public class CardViewHolder<T> extends RecyclerView.ViewHolder {

    private final ViewDataBinding binding;

    public CardViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(T item) {
        binding.setVariable(BR.item, item);
        binding.executePendingBindings();
    }

    public void setClickListener(View.OnClickListener listener) {
       binding.getRoot().setOnClickListener(listener);
    }

    public void setRemoveClickListener(View.OnClickListener listener) {
        View deleteButton = binding.getRoot().findViewById(R.id.deleteButton);
        if (deleteButton != null) {deleteButton.setOnClickListener(listener);}
    }
}
