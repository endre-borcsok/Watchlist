package com.ebsoft.watchlist.network;

import com.ebsoft.watchlist.data.model.db.Stock;

public interface QuoteQueryListener {

    void onComplete(Stock stock);
}
