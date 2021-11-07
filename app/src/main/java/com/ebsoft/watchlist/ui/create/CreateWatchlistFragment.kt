package com.ebsoft.watchlist.ui.create

import com.ebsoft.watchlist.BR
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.databinding.FragmentCreateWatchlistBinding
import com.ebsoft.watchlist.ui.base.BaseFragment

import javax.inject.Inject

import androidx.navigation.Navigation

class CreateWatchlistFragment : BaseFragment<FragmentCreateWatchlistBinding, CreateWatchlistViewModel>(), CreateWatchlistNavigator {

    override fun setup() {
        viewModel.navigator = this
        activity!!.setTitle(R.string.create_watchlist_fragment_label)
        showKeyboard()
    }

    override fun onWatchlistCreated() {
        Navigation.findNavController(view!!).navigateUp()
    }
}
