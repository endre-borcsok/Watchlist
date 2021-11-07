package com.ebsoft.watchlist.ui.search

import androidx.navigation.Navigation
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.model.db.Stock
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.data.model.yahoo.Item
import com.ebsoft.watchlist.databinding.FragmentSearchBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.ui.adapter.ListItemClickListener
import com.ebsoft.watchlist.ui.base.BaseFragment
import com.ebsoft.watchlist.ui.base.BaseNavigator
import com.ebsoft.watchlist.utils.Constants
import javax.inject.Inject

class SearchFragment : BaseFragment<BaseNavigator, FragmentSearchBinding, SearchViewModel>(), android.support.v7.widget.SearchView.OnQueryTextListener, ListItemClickListener<Item> {

    @Inject
    lateinit var mAdapter: ListAdapter<Item>

    override fun setup() {
        showKeyboard()
    }

    override fun onQueryTextSubmit(s: String): Boolean {
        viewModel.performSearch(s)
        return false
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }

    override fun onItemClick(item: Item) {
        val wlist = arguments!!.getSerializable(Constants.EXTRA_KEY_WATCHLIST) as Watchlist
        viewModel.insertStock(Stock.create(item, wlist))
        Navigation.findNavController(view!!).navigateUp()
    }
}
