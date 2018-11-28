package com.ebsoft.watchlist.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle

import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity() {

    private var mViewDataBinding: T? = null

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        bindData()
    }

    private fun injectDependencies() {
        AndroidInjection.inject(this)
    }

    private fun bindData() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewDataBinding!!.executePendingBindings()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
