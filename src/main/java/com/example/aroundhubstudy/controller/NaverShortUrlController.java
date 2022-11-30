package com.example.aroundhubstudy.controller;

import com.example.aroundhubstudy.dto.NaverUrlDto;
import com.example.aroundhubstudy.service.NaverShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NaverShortUrlController {
    @Value("${naver.client.id}")
    private String CLIENT_ID;

    @Value("${naver.client.secret}")
    private String CLIENT_SECRET;

    @Autowired
    NaverShortUrlService naverShortUrlService;

    @GetMapping("/shorturl")
    public ResponseEntity<NaverUrlDto> generateShortUrl(@RequestParam String originUrl){
        log.info("generateShortUrl from: {}", originUrl);
        return naverShortUrlService.requestShortUrl(CLIENT_ID, CLIENT_SECRET, originUrl);
    }

}
