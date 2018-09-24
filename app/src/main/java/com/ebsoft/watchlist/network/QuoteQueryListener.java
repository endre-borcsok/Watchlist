package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.db.Symbol;

public interface QuoteQueryListener {

    void onComplete(Symbol symbol);
}
