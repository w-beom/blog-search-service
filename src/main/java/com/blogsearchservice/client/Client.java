package com.blogsearchservice.client;

import org.springframework.http.HttpEntity;

import java.net.URI;

public interface Client {

    URI createUri(String query, String sort, int page, int size);

    HttpEntity<String> createHttpEntity();
}
