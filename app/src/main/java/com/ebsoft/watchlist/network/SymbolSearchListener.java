package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.yahoo.Item;

import java.util.List;

public interface SymbolSearchListener {

    void onComplete(List<Item> list);
}
