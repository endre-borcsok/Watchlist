package com.ebsoft.watchlist.ui.main;

import com.ebsoft.watchlist.data.model.db.Watchlist;

/**
 * Created by endre on 21/09/18.
 */

public interface WatchlistListener {

    void onWatchlistSelected(Watchlist watchlist);
}
