package com.ebsoft.watchlist.ui.main;

import android.view.View;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.BaseCardViewHolder;

public class MainViewHolder extends BaseCardViewHolder<Watchlist> {
    public TextView mTextView;

    public MainViewHolder(View v) {
        super(v);
        mTextView = v.findViewById(R.id.cardViewTitle);
    }

    @Override
    public void onBindViewHolder(Watchlist item) {
        mTextView.setText(item.getName());
    }
}
