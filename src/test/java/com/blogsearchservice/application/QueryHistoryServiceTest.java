package com.blogsearchservice.application;

import com.blogsearchservice.domain.QueryHistory;
import com.blogsearchservice.domain.QueryRepository;
import com.blogsearchservice.presentation.dto.PopularQueryResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QueryHistoryServiceTest {

    @Autowired
    private QueryHistoryService queryHistoryService;
    @Autowired
    private QueryRepository queryRepository;


    @Test
    @Transactional
    @DisplayName("queryHistory가 존재하면 queryCount가 1 증가한다.")
    void updateQueryHistoryTest() {
        String query = "테스트";
        QueryHistory queryHistory = new QueryHistory(query, 1L);
        queryRepository.save(queryHistory);

        queryHistoryService.updateQueryHistory(query);

        Optional<QueryHistory> findQueryHistory = queryRepository.findByQuery(query);

        assertEquals(2L, findQueryHistory.get().getQueryCount());
    }

    @Test
    @Transactional
    @DisplayName("queryHistory가 존재하지 않으면 DB에 저장한다.")
    void updateQueryHistoryTest2() {
        String query = "테스트";

        queryHistoryService.updateQueryHistory(query);

        Optional<QueryHistory> findQueryHistory = queryRepository.findByQuery(query);

        assertEquals(1L, findQueryHistory.get().getQueryCount());
    }

    @Test
    @DisplayName("인기 검색어 목록의 사이즈는 총 10개이다.")
    void findPopularQueriesTest() {
        List<QueryHistory> queryHistories = List.of(
                new QueryHistory("1", 10L),
                new QueryHistory("2", 5L),
                new QueryHistory("3", 4L),
                new QueryHistory("4", 3L),
                new QueryHistory("5", 2L),
                new QueryHistory("6", 44L),
                new QueryHistory("7", 5L),
                new QueryHistory("8", 6L),
                new QueryHistory("9", 7L),
                new QueryHistory("10", 8L),
                new QueryHistory("11", 1L),
                new QueryHistory("12", 1L));

        queryRepository.saveAll(queryHistories);

        List<PopularQueryResponse> popularQueries = queryHistoryService.findPopularQueries();

        assertEquals(10, popularQueries.size());
    }

}
