package com.ebsoft.watchlist.ui.main;

import android.view.View;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.WatchlistApplication;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.BaseCardViewHolder;

class MainViewHolder extends BaseCardViewHolder<Watchlist> {
    private final TextView mTextView;
    private final TextView mNumberOfItems;


    public MainViewHolder(View v) {
        super(v);
        mTextView = v.findViewById(R.id.cardViewTitle);
        mNumberOfItems = v.findViewById(R.id.numberOfItems);
    }

    @Override
    public void onBindViewHolder(Watchlist item) {
        mTextView.setText(item.getName());
        mNumberOfItems.setText(String.format(mNumberOfItems.getContext()
                        .getString(R.string.item_count), item.getStocks().size()));
    }
}
