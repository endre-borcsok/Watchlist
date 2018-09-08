package com.ebsoft.watchlist.ui.main;

import android.databinding.ObservableField;

import com.ebsoft.watchlist.data.DataManager;
import com.ebsoft.watchlist.ui.base.BaseViewModel;

/**
 * Created by endre on 07/09/18.
 */

public class MainViewModel extends BaseViewModel {

    private final ObservableField<String> helloWorldText = new ObservableField<>();

    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public ObservableField<String> getHelloWorldText() {
        return helloWorldText;
    }

    public void setHelloWorldText(String text) {
        helloWorldText.set(text);
    }
}
