package com.blogsearchservice.client;

import org.springframework.http.HttpEntity;

import java.net.URI;

public abstract class Client {
    private static final String BLOG_SEARCH_API_URL = "";
    private String apiKey = "";

    public abstract URI createUri(String query, String sort, int page, int size);

    public abstract HttpEntity<String> createHttpEntity();
}
