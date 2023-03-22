package com.blogsearchservice.presentation.dto;

public class PopularQueryResponse {
    private String query;
    private long queryCount;

    public PopularQueryResponse() {
    }

    public PopularQueryResponse(String query, long queryCount) {
        this.query = query;
        this.queryCount = queryCount;
    }

    public String getQuery() {
        return query;
    }

    public long getQueryCount() {
        return queryCount;
    }
}
