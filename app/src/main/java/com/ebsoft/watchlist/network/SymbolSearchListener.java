package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.Yahoo.Item;

import java.util.List;

public interface SymbolSearchListener {

    void onComplete(List<Item> list);
}
