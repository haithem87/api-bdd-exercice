package com.example.api.utils;

import org.springframework.web.client.RestTemplate;

public class RestConfig {

    private static final String BASE_URL = "https://fakestoreapi.com";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
