package com.example.aroundhubstudy.service;

import com.example.aroundhubstudy.dto.NaverUrlDto;
import com.example.aroundhubstudy.entity.ShortUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class NaverShortUrlService {

    private WebClient webClient;

    @Value("${naver.shortUrl}")
    private String NAVER_SHORT_URL;

    public NaverShortUrlService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<NaverUrlDto> requestShortUrl(String clientId, String clientSecret, String originUrl){
        try{

//            String url = UriComponentsBuilder.fromUriString(NAVER_SHORT_URL)
//                    .queryParam("url", originUrl)
//                    .build()
//                    .toUriString();

            String url = "https://openapi.naver.com/v1/util/shorturl?url=http://utlee.duckdns.org";
            ResponseEntity<NaverUrlDto> response = webClient.get()
                    .uri(url)
                    .headers(httpHeaders -> {
                        httpHeaders.add(HttpHeaders.CONTENT_TYPE, String.valueOf(MediaType.APPLICATION_JSON));
                        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
                        httpHeaders.add("X-Naver-Client-Id", clientId);
                        httpHeaders.add("X-Naver-Client-Secret", clientSecret);
                    })
                    .retrieve()
                    .toEntity(NaverUrlDto.class)
                    .block();
            return response;

        } catch (WebClientResponseException e){
            log.error("response code : {}, response body : {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClient Exception", e);
            throw e;
        } catch (Exception e){
            throw e;
        }
    }


}
