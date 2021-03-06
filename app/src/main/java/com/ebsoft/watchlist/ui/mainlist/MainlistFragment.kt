package com.ebsoft.watchlist.ui.mainlist

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager

import com.ebsoft.watchlist.BR
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.databinding.FragmentMainlistBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.ui.adapter.ListItemClickListener
import com.ebsoft.watchlist.ui.adapter.ListItemRemoveListener
import com.ebsoft.watchlist.ui.base.BaseFragment
import com.ebsoft.watchlist.ui.dialog.DeleteDialog
import com.ebsoft.watchlist.utils.Constants

import javax.inject.Inject

import androidx.navigation.Navigation

class MainlistFragment : BaseFragment<FragmentMainlistBinding, MainlistViewModel>(), MainlistNavigator, DeleteDialog.DeleteDialogListener, ListItemClickListener<Watchlist>, ListItemRemoveListener<Watchlist> {

    @Inject
    override lateinit var viewModel: MainlistViewModel

    @Inject
    lateinit var mAdapter: ListAdapter<Watchlist>

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_mainlist

    override fun setup() {
        activity!!.setTitle(R.string.main_fragment_label)
        val viewDataBinding = viewDataBinding
        viewDataBinding!!.mainActivityRecyclerView.itemAnimator = DefaultItemAnimator()
        viewDataBinding.mainActivityRecyclerView.adapter = mAdapter
        viewDataBinding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(activity)
        viewModel.navigator = this
        viewModel.subscribeToLiveData(this)
        mAdapter.setItemClickListener(this)
        mAdapter.setItemRemoveListener(this)
    }

    override fun onActionButtonClick() {
        Navigation.findNavController(view!!).navigate(R.id.createWatchlistFragment)
    }

    override fun onItemClick(item: Watchlist) {
        val bundle = Bundle()
        bundle.putSerializable(Constants.EXTRA_KEY_WATCHLIST, item)
        Navigation.findNavController(view!!).navigate(R.id.watchlistFragment, bundle)
    }

    override fun onRemove(item: Watchlist) {
        val dialog = DeleteDialog()
        dialog.setListener(this)
        val args = Bundle()
        args.putSerializable(Constants.DELETE_DIALOG_SERIALIZABLE_KEY, item)
        dialog.arguments = args
        dialog.show(activity!!.supportFragmentManager, this.javaClass.simpleName)
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        viewModel.deleteWatchlist(dialog.arguments!!
                .getSerializable(Constants.DELETE_DIALOG_SERIALIZABLE_KEY) as Watchlist)
    }
}
