package com.ebsoft.watchlist.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Watchlist;
import com.ebsoft.watchlist.ui.adapter.BindableAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class WatchlistAdapter extends RecyclerView.Adapter<ViewHolder>
        implements BindableAdapter<List<Watchlist>> {

    private List<Watchlist> mDataSet;
    private WatchlistListener mWatchlistListener;

    public WatchlistAdapter() {
        mDataSet = new ArrayList<>();
    }

    public void setWatchlistListener(WatchlistListener listener) {
        mWatchlistListener = listener;
    }

    @Override
    public void addItems(List<Watchlist> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    @Override
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
        holder.mTextView.setText(mDataSet.get(position).getName());
        holder.mView.setOnClickListener(getOnClickListenerForPosition(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private View.OnClickListener getOnClickListenerForPosition(int position) {
        return view -> {
            if (mWatchlistListener != null) {
                mWatchlistListener.onWatchlistSelected(mDataSet.get(position));
            }
        };
    }
}