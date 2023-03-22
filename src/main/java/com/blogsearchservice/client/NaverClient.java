package com.blogsearchservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Stream;

@Component
@Order(2)
public class NaverClient implements Client {
    private static final String BLOG_SEARCH_API_URL = "https://openapi.naver.com/v1/search/blog";

    @Value("${api.naver.clientId}")
    private String clientId;
    @Value("${api.naver.clientSecret}")
    private String clientSecret;

    enum Sort {
        SIM,
        DATE;

        public static Sort getSort(String findSort) {
            return Stream.of(Sort.values())
                    .filter(sort -> sort.name().equals(findSort))
                    .findFirst()
                    .orElse(SIM);
        }
    }

    @Override
    public URI createUri(String query, String sort, int start, int display) {
        return URI.create(UriComponentsBuilder.fromUriString(BLOG_SEARCH_API_URL)
                .queryParam("query", query)
                .queryParam("sort", Sort.getSort(sort))
                .queryParam("start", start)
                .queryParam("display", display)
                .toUriString());
    }

    @Override
    public HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.set(HttpHeaders.ACCEPT_CHARSET, MediaType.APPLICATION_JSON_VALUE);

        return new HttpEntity<>(headers);
    }
}
