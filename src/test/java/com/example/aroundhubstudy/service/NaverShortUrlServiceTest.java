package com.example.aroundhubstudy.service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

class NaverShortUrlServiceTest {
    @Value("${naver.shortUrl}")
    private String NAVER_SHORT_URL_ENDPOINT;

    @Value("${naver.clientId}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.clientSecret}")
    private String NAVER_CLIENT_SECRET;

    private WebClient webClient = WebClient.create(NAVER_SHORT_URL_ENDPOINT);

    @Autowired
    NaverShortUrlService naverShortUrlService;


    @Test
    void requestShortUrl() {
        String originUrl = "http://utlee.duckdns.org";

        String url = UriComponentsBuilder.fromHttpUrl(NAVER_SHORT_URL_ENDPOINT)
                .queryParam("url", originUrl)
                .build()
                .toUriString();

        //String url = "https://openapi.naver.com/v1/util/shorturl";

        naverShortUrlService.getShortUrl(url);
    }
}