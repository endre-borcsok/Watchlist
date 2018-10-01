package com.ebsoft.watchlist.ui.search;

import android.view.View;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.ui.adapter.BaseCardViewHolder;

public class SearchViewHolder extends BaseCardViewHolder<String> {
    private final TextView mTextView;

    public SearchViewHolder(View v) {
        super(v);
        mTextView = v.findViewById(R.id.cardViewTitle);
    }

    @Override
    public void onBindViewHolder(String item) {
        mTextView.setText(item);
    }
}