package com.ebsoft.watchlist.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        bindData();
        setup();
    }

    public abstract void setup();

    public abstract int getLayoutId();

    public abstract V getViewModel();

    public T getViewDatabinding() {
        return mViewDataBinding;
    }

    public abstract int getBindingVariable();

    private void injectDependencies() {
        AndroidInjection.inject(this);
    }

    private void bindData() {
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }
}
