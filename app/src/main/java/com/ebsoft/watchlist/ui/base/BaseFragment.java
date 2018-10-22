package com.ebsoft.watchlist.ui.base;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.ebsoft.watchlist.ui.main.MainActivity;
import com.ebsoft.watchlist.ui.mainlist.MainlistFragment;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel>
        extends DaggerFragment {

    private T mViewDataBinding;

    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract int getLayoutId();

    public abstract V getViewModel();

    public abstract void setup();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onAttach(Context context) {
        injectDependencies();
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        hideKeyboard();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
        setup();
        prepareNavigationBar();
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    private void prepareNavigationBar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(!(this instanceof MainlistFragment));
    }

    private void injectDependencies() {
        AndroidSupportInjection.inject(this);
    }
}
