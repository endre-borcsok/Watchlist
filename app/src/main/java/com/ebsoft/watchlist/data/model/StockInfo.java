package com.ebsoft.watchlist.data.model;

import android.support.annotation.NonNull;

public interface StockInfo {

    String getSymbol();

    float getPrice();

    float getChange();

    float getChangePercent();
}
