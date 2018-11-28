package com.ebsoft.watchlist.ui.watchlist

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager

import com.ebsoft.watchlist.BR
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.databinding.FragmentWatchlistBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.ui.adapter.ListItemClickListener
import com.ebsoft.watchlist.ui.adapter.ListItemRemoveListener
import com.ebsoft.watchlist.ui.base.BaseFragment
import com.ebsoft.watchlist.ui.dialog.DeleteDialog
import com.ebsoft.watchlist.utils.Constants

import javax.inject.Inject

import androidx.navigation.Navigation

class WatchlistFragment : BaseFragment<FragmentWatchlistBinding, WatchlistViewModel>(), WatchlistNavigator, DeleteDialog.DeleteDialogListener, SwipeRefreshLayout.OnRefreshListener, ListItemClickListener<Stock>, ListItemRemoveListener<Stock> {

    @Inject
    override lateinit var viewModel: WatchlistViewModel

    @Inject
    lateinit var mAdapter: ListAdapter<Stock>

    override val layoutId: Int
        get() = R.layout.fragment_watchlist

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun setup() {
        mAdapter.setItemClickListener(this)
        mAdapter.setItemRemoveListener(this)
        val viewDataBinding = viewDataBinding
        viewDataBinding!!.watchlistRecyclerView.layoutManager = LinearLayoutManager(context)
        viewDataBinding.watchlistRecyclerView.itemAnimator = DefaultItemAnimator()
        viewDataBinding.watchlistRecyclerView.adapter = mAdapter
        viewDataBinding.swipeRefresh.setOnRefreshListener(this)
        viewModel.navigator = this
        viewModel.subscribeToLiveData(this)
        activity!!.title = viewModel.watchlist.name
    }

    override fun onActionButtonClick() {
        val bundle = Bundle()
        bundle.putSerializable(Constants.EXTRA_KEY_WATCHLIST, viewModel.watchlist)
        Navigation.findNavController(view!!).navigate(R.id.searchFragment, bundle)
    }

    override fun onItemClick(item: Stock) {
        // TODO
    }

    override fun onRemove(item: Stock) {
        val dialog = DeleteDialog()
        dialog.setListener(this)
        val args = Bundle()
        args.putSerializable(Constants.DELETE_DIALOG_SERIALIZABLE_KEY, item)
        dialog.arguments = args
        dialog.show(activity!!.supportFragmentManager, this.javaClass.simpleName)
    }

    override fun onRefresh() {
        viewModel.refresh()
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.removeStock(dialog.arguments!!
                .getSerializable(Constants.DELETE_DIALOG_SERIALIZABLE_KEY) as Stock)
    }
}
