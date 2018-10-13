package com.ebsoft.watchlist.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.ebsoft.watchlist.ui.main.MainActivity;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        bindData();
        prepareNavigationBar();
        setup();
    }

    public abstract int getLayoutId();

    public abstract int getBindingVariable();

    public abstract V getViewModel();

    public abstract void setup();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void injectDependencies() {
        AndroidInjection.inject(this);
    }

    private void bindData() {
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    private void prepareNavigationBar() {
        if (!(this instanceof MainActivity)) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
