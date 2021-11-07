package com.ebsoft.watchlist.ui.base

import android.app.Activity
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.ebsoft.watchlist.BR
import com.ebsoft.watchlist.R
import com.ebsoft.watchlist.ui.mainlist.MainlistFragment
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<N: BaseNavigator, T : ViewDataBinding, V : BaseViewModel<N>> : DaggerFragment() {

    @Inject
    lateinit var viewDataBinding: T

    @Inject
    lateinit var viewModel: V

    private val bindingVariable: Int
        get() = BR.viewModel

    abstract fun setup()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?)
    : View? = viewDataBinding.root

    override fun onDetach() {
        super.onDetach()
        hideKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(bindingVariable, viewModel)
        viewDataBinding.executePendingBindings()
        viewModel.navigator = this as N
        viewModel.subscribeToLiveData(this)
        activity!!.setTitle(R.string.create_watchlist_fragment_label)
        setup()
        prepareNavigationBar()
    }

    fun showKeyboard() {
        val imm = context!!
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard() {
        try {
            val imm = (context!!
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            imm.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {}
    }

    private fun prepareNavigationBar() {
        (activity as AppCompatActivity).supportActionBar!!
                .setDisplayHomeAsUpEnabled(this !is MainlistFragment)
    }
}
