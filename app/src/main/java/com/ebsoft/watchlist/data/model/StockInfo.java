package com.ebsoft.watchlist.data.model;

public interface StockInfo {

    String getSymbol();

    float getPrice();

    float getChange();

    float getChangePercent();
}
