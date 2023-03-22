package com.blogsearchservice.client;

import com.blogsearchservice.application.QueryHistoryService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiClient {
    private final RestTemplate restTemplate;
    private final QueryHistoryService searchService;
    private final List<Client> clients;
    private final NaverClient naverClient;


    public ApiClient(RestTemplate restTemplate, QueryHistoryService searchService, List<Client> clients, NaverClient naverClient) {
        this.restTemplate = restTemplate;
        this.searchService = searchService;
        this.clients = clients;
        this.naverClient = naverClient;
    }

    @Transactional
    public String searchBlogs(String query, String sort, int page, int size) {
        ResponseEntity<String> exchange = restTemplate.exchange(naverClient.createUri(query, sort, page, size), HttpMethod.GET, naverClient.createHttpEntity(), String.class);

        searchService.updateQueryHistory(query);

        return exchange.getBody();
    }
}
