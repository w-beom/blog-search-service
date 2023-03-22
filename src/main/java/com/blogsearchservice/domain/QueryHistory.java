package com.blogsearchservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class QueryHistory {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String query;
    @Column
    private Long queryCount;

    public QueryHistory() {
    }

    public QueryHistory(String query, Long queryCount) {
        this.query = query;
        this.queryCount = queryCount;
    }

    public String getQuery() {
        return query;
    }

    public Long getQueryCount() {
        return queryCount;
    }

    public void incrementQueryCount() {
        this.queryCount += 1L;
    }
}
