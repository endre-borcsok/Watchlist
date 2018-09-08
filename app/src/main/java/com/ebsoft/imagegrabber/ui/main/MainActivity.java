package com.ebsoft.imagegrabber.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import com.ebsoft.imagegrabber.BR;
import com.ebsoft.imagegrabber.R;
import com.ebsoft.imagegrabber.databinding.ActivityMainBinding;
import com.ebsoft.imagegrabber.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private MainViewModel mMainViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(MainViewModel.class);
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mMainViewModel.setHelloWorldText("dkljfa;skldjfas;dfj");
            }
        }.start();
        return mMainViewModel;
    }
}
