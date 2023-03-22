package com.blogsearchservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Order(1)
public class KakaoClient implements Client{
    private static final String BLOG_SEARCH_API_URL = "https://dapi.kakao.com/v2/search/blog";

    @Value("${api.kakao.key}")
    private String apiKey;


    @Override
    public URI createUri(String query, String sort, int page, int size) {
        return URI.create(UriComponentsBuilder.fromUriString(BLOG_SEARCH_API_URL)
                .queryParam("query", query)
                .queryParam("sort", sort)
                .queryParam("page", page)
                .queryParam("size", size)
                .toUriString());
    }

    @Override
    public HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + apiKey);
        headers.set(HttpHeaders.ACCEPT_CHARSET, MediaType.APPLICATION_JSON_VALUE);

        return new HttpEntity<>(headers);
    }
}
