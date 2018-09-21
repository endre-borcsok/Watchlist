package com.ebsoft.watchlist.ui.watchlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.ViewHolder> {

    private List<String> mDataSet;
    private SymbolListener mSymbolListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mTextView = v.findViewById(R.id.recyclerViewItemText);
        }
    }

    public SymbolAdapter() {
        mDataSet = new ArrayList<>();
    }

    public void setSymbolListener(SymbolListener listener) {
        mSymbolListener = listener;
    }

    public void addItems(List<String> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mDataSet.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_view_element, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position));
        holder.mView.setOnClickListener(getOnClickListenerForPosition(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private View.OnClickListener getOnClickListenerForPosition(int position) {
        return view -> {
            if (mSymbolListener != null) {
                mSymbolListener.onSymbolSelected(mDataSet.get(position));
            }
        };
    }
}
