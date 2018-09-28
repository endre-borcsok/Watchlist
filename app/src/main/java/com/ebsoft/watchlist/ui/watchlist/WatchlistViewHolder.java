package com.ebsoft.watchlist.ui.watchlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.ui.adapter.BaseCardViewHolder;

class WatchlistViewHolder extends BaseCardViewHolder<Stock> {
    private final TextView mTextView;
    private final ImageView mDeleteButton;

    public WatchlistViewHolder(View v) {
        super(v);
        mTextView = v.findViewById(R.id.cardViewTitle);
        mDeleteButton = v.findViewById(R.id.deleteButton);
    }

    @Override
    public void onBindViewHolder(Stock item) {
        mTextView.setText(item.getSymbol() + " " + item.getPrice());
    }

    @Override
    public void setRemoveClickListener(View.OnClickListener listener) {
        mDeleteButton.setOnClickListener(listener);
    }
}