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
    @Autowired
    NaverShortUrlService naverShortUrlService;

    @GetMapping("/shorturl-v1")
    public ResponseEntity<NaverUrlDto> generateShortUrlv1(@RequestParam String originUrl){
        /* RestTemplate 사용해서 구현 */
        return naverShortUrlService.requestShortUrlv1(originUrl);
    }

    @GetMapping("/shorturl-v2")
    public ResponseEntity<NaverUrlDto> generateShortUrlv2(@RequestParam String originUrl){
        /* WebClient 사용해서 구현 */
        return naverShortUrlService.requestShortUrlv2(originUrl);
    }

}
