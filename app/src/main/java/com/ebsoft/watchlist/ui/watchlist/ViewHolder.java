package com.ebsoft.watchlist.ui.watchlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ebsoft.watchlist.R;

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public View mView;

    public ViewHolder(View v) {
        super(v);
        mView = v;
        mTextView = v.findViewById(R.id.cardViewTitle);
    }
}