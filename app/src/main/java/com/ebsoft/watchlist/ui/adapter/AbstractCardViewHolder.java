package com.ebsoft.watchlist.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractCardViewHolder<T> extends RecyclerView.ViewHolder {

    private final View mView;

    public AbstractCardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = itemView;
    }

    public abstract void onBindViewHolder(T item);

    public void setClickListener(View.OnClickListener listener) {
       mView.setOnClickListener(listener);
    }

    public void setRemoveClickListener(View.OnClickListener listener) {}
}
