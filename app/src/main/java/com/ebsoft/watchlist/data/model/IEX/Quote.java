
package com.ebsoft.watchlist.data.model.IEX;
import com.ebsoft.watchlist.data.model.StockInfo;
import com.ebsoft.watchlist.utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote implements StockInfo {

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("latestPrice")
    @Expose
    private Double latestPrice;

    @SerializedName("change")
    @Expose
    private Double change;

    @SerializedName("changePercent")
    @Expose
    private Double changePercent;


    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public float getPrice() {
        return latestPrice.floatValue();
    }

    @Override
    public float getChange() {
        return change.floatValue() * Constants.IEX_API_PERCENTAGE_MULTIPLIER;
    }

    @Override
    public float getChangePercent() {
        return changePercent.floatValue() * Constants.IEX_API_PERCENTAGE_MULTIPLIER;
    }
}