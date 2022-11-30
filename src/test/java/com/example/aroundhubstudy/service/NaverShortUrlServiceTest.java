package com.example.aroundhubstudy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

class NaverShortUrlServiceTest {

    private WebClient webClient = WebClient.create("https://openapi.naver.com/v1/util/shorturl?url=http://utlee.duckdns.org");

    @Value("${naver.shortUrl}")
    private String NAVER_SHORT_URL = "https://openapi.naver.com/v1/util/shorturl";

    @Value("${naver.clientId}")
    private String CLIENT_ID = "DIQ9kSya80pQ27fPvpk_";

    @Value("${naver.clientSecret}")
    private String CLIENT_SECRET= "37dTiG6CHT";


    NaverShortUrlService naverShortUrlService = new NaverShortUrlService(webClient);

    @Test
    void requestShortUrl() {
        String originUrl = "http://utlee.duckdns.org";

        String url = UriComponentsBuilder.fromUriString(NAVER_SHORT_URL)
                .queryParam("url", originUrl)
                .build()
                .toUriString();

        //String url = "https://openapi.naver.com/v1/util/shorturl";

        naverShortUrlService.requestShortUrl(CLIENT_ID, CLIENT_SECRET, url);
    }
}