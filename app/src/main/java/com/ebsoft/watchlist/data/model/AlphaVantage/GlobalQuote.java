package com.ebsoft.watchlist.data.model.AlphaVantage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalQuote {

    @SerializedName("01. symbol")
    @Expose
    private String _01Symbol;
    @SerializedName("02. open")
    @Expose
    private String _02Open;
    @SerializedName("03. high")
    @Expose
    private String _03High;
    @SerializedName("04. low")
    @Expose
    private String _04Low;
    @SerializedName("05. price")
    @Expose
    private String _05Price;
    @SerializedName("06. volume")
    @Expose
    private String _06Volume;
    @SerializedName("07. latest trading day")
    @Expose
    private String _07LatestTradingDay;
    @SerializedName("08. previous close")
    @Expose
    private String _08PreviousClose;
    @SerializedName("09. change")
    @Expose
    private String _09Change;
    @SerializedName("10. change percent")
    @Expose
    private String _10ChangePercent;

    public String getSymbol() {
        return _01Symbol;
    }

    public void setSymbol(String _01Symbol) {
        this._01Symbol = _01Symbol;
    }

    public String getOpen() {
        return _02Open;
    }

    public void setOpen(String _02Open) {
        this._02Open = _02Open;
    }

    public String getHigh() {
        return _03High;
    }

    public void setHigh(String _03High) {
        this._03High = _03High;
    }

    public String getLow() {
        return _04Low;
    }

    public void setLow(String _04Low) {
        this._04Low = _04Low;
    }

    public String getPrice() {
        return _05Price;
    }

    public void setPrice(String _05Price) {
        this._05Price = _05Price;
    }

    public String getVolume() {
        return _06Volume;
    }

    public void setVolume(String _06Volume) {
        this._06Volume = _06Volume;
    }

    public String getLatestTradingDay() {
        return _07LatestTradingDay;
    }

    public void setLatestTradingDay(String _07LatestTradingDay) {
        this._07LatestTradingDay = _07LatestTradingDay;
    }

    public String getPreviousClose() {
        return _08PreviousClose;
    }

    public void setPreviousClose(String _08PreviousClose) {
        this._08PreviousClose = _08PreviousClose;
    }

    public String getChange() {
        return _09Change;
    }

    public void setChange(String _09Change) {
        this._09Change = _09Change;
    }

    public String getChangePercent() {
        return _10ChangePercent;
    }

    public void setChangePercent(String _10ChangePercent) {
        this._10ChangePercent = _10ChangePercent;
    }

}
