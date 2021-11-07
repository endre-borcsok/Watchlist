package com.ebsoft.watchlist.ui.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * Created by endre on 08/09/18.
 */

class ListAdapter<E>(private val mLayoutId: Int) : RecyclerView.Adapter<ListViewHolder<E>>(), BindableAdapter<List<E>> {

    private val mDataSet: MutableList<E>

    private var mItemClickListener: ListItemClickListener<E>? = null

    private var mItemRemoveListener: ListItemRemoveListener<E>? = null

    init {
        this.mDataSet = ArrayList()
    }

    fun setItemClickListener(listener: ListItemClickListener<E>) {
        this.mItemClickListener = listener
    }

    fun setItemRemoveListener(listener: ListItemRemoveListener<E>) {
        this.mItemRemoveListener = listener
    }

    override fun addItems(items: List<E>) {
        mDataSet.addAll(items)
        notifyDataSetChanged()
    }

    override fun clearItems() {
        mDataSet.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<E> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, viewType, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder<E>, position: Int) {
        viewHolder.bind(mDataSet[position])
        viewHolder.setClickListener(getOnClickListenerForPosition(position))
        viewHolder.setRemoveClickListener(getItemRemoveListenerForPosition(position))
    }

    override fun getItemViewType(position: Int): Int {
        return mLayoutId
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    private fun getOnClickListenerForPosition(position: Int): View.OnClickListener {
        return View.OnClickListener {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(mDataSet[position])
            }
        }
    }

    private fun getItemRemoveListenerForPosition(position: Int): View.OnClickListener {
        return View.OnClickListener {
            if (mItemRemoveListener != null) {
                mItemRemoveListener!!.onRemove(mDataSet[position])
            }
        }
    }
}
