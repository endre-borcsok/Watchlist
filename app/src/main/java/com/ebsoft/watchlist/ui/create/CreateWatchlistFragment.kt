package com.ebsoft.watchlist.ui.create

import androidx.navigation.Navigation
import com.ebsoft.watchlist.databinding.FragmentCreateWatchlistBinding
import com.ebsoft.watchlist.ui.base.BaseFragment

class CreateWatchlistFragment : BaseFragment<CreateWatchlistNavigator, FragmentCreateWatchlistBinding, CreateWatchlistViewModel>(), CreateWatchlistNavigator {

    override fun setup() {
        showKeyboard()
    }

    override fun onWatchlistCreated() {
        Navigation.findNavController(view!!).navigateUp()
    }
}
