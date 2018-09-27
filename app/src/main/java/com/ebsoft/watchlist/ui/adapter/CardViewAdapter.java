package com.ebsoft.watchlist.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class CardViewAdapter<E, T extends BaseCardViewHolder<E>> extends RecyclerView.Adapter<T>
        implements BindableAdapter<List<E>> {

    private final String TAG = CardViewAdapter.class.getSimpleName();

    private final int mLayoutId;

    private final List<E> mDataSet;

    private final Class<T> mViewHolderClass;

    private CardViewItemClickListener<E> mItemClickListener;

    public CardViewAdapter(int layoutId, Class<T> viewHolderClass) {
        this.mLayoutId = layoutId;
        this.mDataSet = new ArrayList<>();
        this.mViewHolderClass = viewHolderClass;
    }

    public void setItemClickListener(CardViewItemClickListener<E> listener) {
        this.mItemClickListener = listener;
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
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutId, parent, false);
        return createViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull T viewHolder, int position) {
        viewHolder.onBindViewHolder(mDataSet.get(position));
        viewHolder.getView().setOnClickListener(getOnClickListenerForPosition(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private T createViewHolder(View v) {
        try {
            return mViewHolderClass.getConstructor(View.class).newInstance(v);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    private View.OnClickListener getOnClickListenerForPosition(int position) {
        return view -> {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(mDataSet.get(position));
            }
        };
    }
}
