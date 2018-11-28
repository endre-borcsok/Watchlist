package com.ebsoft.watchlist.ui.base

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import com.ebsoft.watchlist.ui.mainlist.MainlistFragment

import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : DaggerFragment() {

    var viewDataBinding: T? = null
        private set

    private var mViewModel: V? = null

    abstract val bindingVariable: Int

    abstract val layoutId: Int

    abstract val viewModel: V

    abstract fun setup()

    override fun onAttach(context: Context?) {
        injectDependencies()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding!!.root
    }

    override fun onDetach() {
        super.onDetach()
        hideKeyboard()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
        setup()
        prepareNavigationBar()
    }

    fun showKeyboard() {
        val imm = context!!
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard() {
        val imm = context!!
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, 0)
    }

    private fun prepareNavigationBar() {
        (activity as AppCompatActivity).supportActionBar!!
                .setDisplayHomeAsUpEnabled(this !is MainlistFragment)
    }

    private fun injectDependencies() {
        AndroidSupportInjection.inject(this)
    }
}
