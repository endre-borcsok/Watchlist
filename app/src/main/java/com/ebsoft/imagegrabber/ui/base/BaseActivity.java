package com.ebsoft.imagegrabber.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        bindData();
    }

    public abstract int getLayoutId();

    public abstract V getViewModel();

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
