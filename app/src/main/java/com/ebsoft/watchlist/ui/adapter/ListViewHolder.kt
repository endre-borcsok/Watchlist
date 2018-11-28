package com.ebsoft.watchlist.ui.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

import com.ebsoft.watchlist.BR
import com.ebsoft.watchlist.R

class ListViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

    fun setClickListener(listener: View.OnClickListener) {
        binding.root.setOnClickListener(listener)
    }

    fun setRemoveClickListener(listener: View.OnClickListener) {
        val deleteButton = binding.root.findViewById<View>(R.id.deleteButton)
        deleteButton?.setOnClickListener(listener)
    }
}
