package com.ebsoft.watchlist.ui.watchlist;

import com.ebsoft.watchlist.data.model.db.Stock;

/**
 * Created by endre on 21/09/18.
 */

public interface SymbolListener {

    void onStockSelected(Stock stock);
}
