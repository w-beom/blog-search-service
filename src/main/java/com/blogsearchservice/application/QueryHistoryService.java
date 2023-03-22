package com.blogsearchservice.application;

import com.blogsearchservice.domain.QueryHistory;
import com.blogsearchservice.domain.QueryRepository;
import com.blogsearchservice.presentation.dto.PopularQueryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QueryHistoryService {
    private final QueryRepository queryRepository;

    public QueryHistoryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @Transactional
    public void updateQueryHistory(String query) {
        Optional<QueryHistory> findQueryHistory = queryRepository.findByQuery(query);

        if (findQueryHistory.isEmpty()) {
            QueryHistory queryHistory = new QueryHistory(query, 1L);
            queryRepository.save(queryHistory);
            return;
        }

        QueryHistory queryHistory = findQueryHistory.get();
        queryHistory.incrementQueryCount();
        queryRepository.save(queryHistory);
    }

    public List<PopularQueryResponse> findPopularQueries() {
        List<QueryHistory> popularQueries = queryRepository.findTop10ByOrderByQueryCountDesc();

        return popularQueries.stream()
                .map(queryHistory -> new PopularQueryResponse(queryHistory.getQuery(), queryHistory.getQueryCount()))
                .toList();
    }
}
