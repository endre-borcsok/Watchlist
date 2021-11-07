package com.ebsoft.watchlist.ui.watchlist

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.widget.SwipeRefreshLayout
import androidx.navigation.Navigation
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

class WatchlistFragment : BaseFragment<WatchlistNavigator, FragmentWatchlistBinding, WatchlistViewModel>(), WatchlistNavigator, DeleteDialog.DeleteDialogListener, SwipeRefreshLayout.OnRefreshListener, ListItemClickListener<Stock>, ListItemRemoveListener<Stock> {

    @Inject
    lateinit var mAdapter: ListAdapter<Stock>

    override fun setup() {
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
