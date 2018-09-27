package com.ebsoft.watchlist.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.BaseCardViewHolder;

class ViewHolder extends BaseCardViewHolder<String> {
    public TextView mTextView;

    public ViewHolder(View v) {
        super(v);
        mTextView = v.findViewById(R.id.cardViewTitle);
    }

    @Override
    public void onBindViewHolder(String item) {
        mTextView.setText(item);
    }
}