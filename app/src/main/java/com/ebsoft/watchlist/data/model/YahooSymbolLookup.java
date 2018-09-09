package com.ebsoft.watchlist.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by endre on 09/09/18.
 */

public class YahooSymbolLookup {

    @SerializedName("ResultSet")
    @Expose
    private ResultSet resultSet;

    public YahooSymbolLookup(ResultSet resultSet) {
        super();
        this.resultSet = resultSet;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("resultSet", resultSet).toString();
    }

}
