package com.ebsoft.watchlist.ui.search;

import android.view.View;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.Yahoo.Item;
import com.ebsoft.watchlist.ui.adapter.AbstractCardViewHolder;

public class SearchViewHolder extends AbstractCardViewHolder<Item> {
    private final TextView mSymbol;
    private final TextView mCompany;

    public SearchViewHolder(View v) {
        super(v);
        mSymbol = v.findViewById(R.id.cardViewTitle);
        mCompany = v.findViewById(R.id.stockName);
    }

    @Override
    public void onBindViewHolder(Item item) {
        mSymbol.setText(item.getSymbol());
        mCompany.setText(item.getName());
    }
}