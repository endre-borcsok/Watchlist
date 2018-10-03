package com.ebsoft.watchlist.data.model.AlphaVantage;
import com.ebsoft.watchlist.data.model.StockInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalQuote implements StockInfo {

    @SerializedName("01. symbol")
    @Expose
    private String symbol;

    @SerializedName("05. price")
    @Expose
    private String price;

    @SerializedName("09. change")
    @Expose
    private String change;

    @SerializedName("10. change percent")
    @Expose
    private String changePercent;

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public float getPrice() {
        return Float.parseFloat(price);
    }

    @Override
    public float getChange() {
        return Float.parseFloat(change);
    }

    @Override
    public float getChangePercent() {
        return Float.parseFloat(changePercent.replace("%", ""));
    }
}
