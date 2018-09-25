package com.ebsoft.watchlist.ui.watchlist;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebsoft.watchlist.R;
import com.ebsoft.watchlist.data.model.db.Stock;
import com.ebsoft.watchlist.ui.base.BindableAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by endre on 08/09/18.
 */

public class StockAdapter extends RecyclerView.Adapter<StockViewHolder>
        implements BindableAdapter<List<Stock>> {

    private List<Stock> mDataSet;
    private SymbolListener mSymbolListener;

    public StockAdapter() {
        mDataSet = new ArrayList<>();
    }

    public void setSymbolListener(SymbolListener listener) {
        mSymbolListener = listener;
    }

    @Override
    public void addItems(List<Stock> items) {
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public void clearItems() {
        mDataSet.clear();
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new StockViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.test_layout;
    }

    private View.OnClickListener getOnClickListenerForPosition(int position) {
        return view -> {
            if (mSymbolListener != null) {
                mSymbolListener.onSymbolSelected(mDataSet.get(position).symbol);
            }
        };
    }
}
