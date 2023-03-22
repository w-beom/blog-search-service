package com.blogsearchservice.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryHistoryTest {

    @Test
    @DisplayName("queryCount를 1증가시킨다.")
    void queryCountIncrementTest() {
        QueryHistory queryHistory = new QueryHistory("테스트", 1L);
        queryHistory.incrementQueryCount();

        assertEquals(2L, queryHistory.getQueryCount());
    }

}
