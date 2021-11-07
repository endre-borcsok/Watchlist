package com.ebsoft.watchlist.ui.mainlist

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.data.DataManager
import com.ebsoft.watchlist.data.model.db.Watchlist
import com.ebsoft.watchlist.databinding.FragmentCreateWatchlistBinding
import com.ebsoft.watchlist.databinding.FragmentMainlistBinding
import com.ebsoft.watchlist.ui.adapter.ListAdapter
import com.ebsoft.watchlist.ui.create.CreateWatchlistFragment

import dagger.Module
import dagger.Provides

@Module
class MainlistModule {
    @Provides
    internal fun providesBinding(view: MainlistFragment): FragmentMainlistBinding {
        return FragmentMainlistBinding.inflate(view.layoutInflater)
    }

    @Provides
    internal fun provideMainViewModel(DataManager: DataManager): MainlistViewModel {
        return MainlistViewModel(DataManager)
    }

    @Provides
    internal fun providesAdapter(viewDataBinding: FragmentMainlistBinding, fragment: MainlistFragment): ListAdapter<Watchlist> {
        val adapter = ListAdapter<Watchlist>(R.layout.layout_main_menu_cardview_item)
        adapter.setItemClickListener(fragment)
        adapter.setItemRemoveListener(fragment)
        viewDataBinding.mainActivityRecyclerView.itemAnimator = DefaultItemAnimator()
        viewDataBinding.mainActivityRecyclerView.adapter = adapter
        viewDataBinding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(fragment.context)
        return adapter
    }
}
