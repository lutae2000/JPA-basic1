package com.example.aroundhubstudy.controller;

import com.example.aroundhubstudy.dto.NaverUrlDto;
import com.example.aroundhubstudy.service.NaverShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class NaverShortUrlController {
    @Autowired
    NaverShortUrlService naverShortUrlService;

    @GetMapping("/shorturl-v1")
    public ResponseEntity<NaverUrlDto> requestShortUrlv1(@RequestParam String originUrl){
        /* RestTemplate 사용해서 구현 */
        return naverShortUrlService.requestShortUrlv1(originUrl);
    }

    @GetMapping("/shorturl-v2")
    public ResponseEntity<NaverUrlDto> requestShortUrlv2(@RequestParam String originUrl){
        /* WebClient 사용해서 구현 */
        return naverShortUrlService.requestShortUrlv2(originUrl);
    }

    @PostMapping("/shorturl-v2")
    public ResponseEntity<NaverUrlDto> generateShortUrlv2(@RequestParam String originUrl){
        return naverShortUrlService.generateShortUrl(originUrl);
    }

    @DeleteMapping("/shorturl-v2")
    public ResponseEntity<String> deleteShortUrl_v2(@RequestParam String originUrl){
        naverShortUrlService.deleteShortUrl(originUrl);
        return ResponseEntity.status(HttpStatus.OK).body("삭제완료");
    }

    @GetMapping("/naverShortUrl")
    public ResponseEntity<?> getNaverShortUrl(@RequestParam String originUrl){
        ResponseEntity<?> response = naverShortUrlService.getShortUrl(originUrl);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
