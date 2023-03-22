package com.blogsearchservice.client;

import com.blogsearchservice.application.QueryHistoryService;
import com.blogsearchservice.presentation.exception.ApiRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiClient {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final RestTemplate restTemplate;
    private final QueryHistoryService searchService;
    private final List<Client> clients;


    public ApiClient(RestTemplate restTemplate, QueryHistoryService searchService, List<Client> clients) {
        this.restTemplate = restTemplate;
        this.searchService = searchService;
        this.clients = clients;
    }

    @Transactional
    public String searchBlogs(String query, String sort, int page, int size) {
        for (Client client : clients) {
            try {
                ResponseEntity<String> exchange = restTemplate.exchange(client.createUri(query, sort, page, size), HttpMethod.GET, client.createHttpEntity(), String.class);
                searchService.updateQueryHistory(query);
                return exchange.getBody();
            } catch (Exception e) {
                log.error("요청 실패한 URL : {}", client.createUri(query, sort, page, size));
            }
        }
        throw new ApiRequestException("API를 요청하는데 오류가 발생하였습니다.");
    }
}
