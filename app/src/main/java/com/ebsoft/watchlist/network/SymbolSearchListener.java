package com.ebsoft.watchlist.network;

import java.util.List;

public interface SymbolSearchListener {

    void onComplete(List<String> list);
}
