package com.ebsoft.watchlist.ui.watchlist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.ui.adapter.BaseCardViewHolder;

public class WatchlistViewHolder extends BaseCardViewHolder<Stock> {
    private final TextView mSymbol;
    private final TextView mPrice;
    private final TextView mChangePercent;
    private final ImageView mDeleteButton;

    public WatchlistViewHolder(View v) {
        super(v);
        mSymbol = v.findViewById(R.id.stockSymbol);
        mPrice = v.findViewById(R.id.price);
        mChangePercent = v.findViewById(R.id.changePercent);
        mDeleteButton = v.findViewById(R.id.deleteButton);
    }

    @Override
    public void onBindViewHolder(Stock item) {
        mSymbol.setText(item.getSymbol());
        mPrice.setText(String.format(mPrice.getContext()
                .getString(R.string.stock_item_price), item.getPrice()));
        mChangePercent.setText(String.format(mChangePercent.getContext()
                .getString(R.string.stock_item_change_percent), item.getChangePercent()));
        mPrice.setTextColor(item.getColor());
        mChangePercent.setTextColor(item.getColor());
    }

    @Override
    public void setRemoveClickListener(View.OnClickListener listener) {
        mDeleteButton.setOnClickListener(listener);
    }
}