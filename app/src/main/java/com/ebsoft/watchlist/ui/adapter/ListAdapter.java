package com.ebsoft.watchlist.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class ListAdapter<E> extends RecyclerView.Adapter<ListViewHolder<E>>
        implements BindableAdapter<List<E>> {

    private final int mLayoutId;

    private final List<E> mDataSet;

    private ListItemClickListener<E> mItemClickListener;

    private ListItemRemoveListener<E> mItemRemoveListener;

    public ListAdapter(int layoutId) {
        this.mLayoutId = layoutId;
        this.mDataSet = new ArrayList<>();
    }

    public void setItemClickListener(ListItemClickListener<E> listener) {
        this.mItemClickListener = listener;
    }

    public void setItemRemoveListener(ListItemRemoveListener<E> listener) {
        this.mItemRemoveListener = listener;
    }

    @Override
    public void addItems(List<E> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void clearItems() {
        mDataSet.clear();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder viewHolder, int position) {
        viewHolder.bind(mDataSet.get(position));
        viewHolder.setClickListener(getOnClickListenerForPosition(position));
        viewHolder.setRemoveClickListener(getItemRemoveListenerForPosition(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mLayoutId;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private View.OnClickListener getOnClickListenerForPosition(int position) {
        return view -> {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mDataSet.get(position));
            }
        };
    }

    private View.OnClickListener getItemRemoveListenerForPosition(int position) {
        return view -> {
            if (mItemRemoveListener != null) {
                mItemRemoveListener.onRemove(mDataSet.get(position));
            }
        };
    }
}
