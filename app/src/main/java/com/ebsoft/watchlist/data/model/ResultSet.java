package com.ebsoft.watchlist.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by endre on 09/09/18.
 */

public class ResultSet {

    @SerializedName("Query")
    @Expose
    private String query;
    @SerializedName("Result")
    @Expose
    private List<Result> result = null;

    public ResultSet(String query, List<Result> result) {
        super();
        this.query = query;
        this.result = result;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("query", query).append("result", result).toString();
    }

}