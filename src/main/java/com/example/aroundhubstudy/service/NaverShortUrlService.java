package com.example.aroundhubstudy.service;

import com.example.aroundhubstudy.dto.NaverUrlDto;
import com.example.aroundhubstudy.entity.ShortUrl;

import io.netty.handler.codec.http.HttpScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@Slf4j
public class NaverShortUrlService {

    @Value("${naver.shortUrl}")
    private String NAVER_SHORT_URL_ENDPOINT;

    @Value("${naver.clientId}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.clientSecret}")
    private String NAVER_CLIENT_SECRET;

    /* WebClient 사용해서 구현 */
    public ResponseEntity<NaverUrlDto> requestShortUrlv2(String originUrl){
        try {
            //URI 생성 방법1
            String url = UriComponentsBuilder.fromUriString(NAVER_SHORT_URL_ENDPOINT)
                    .queryParam("url", originUrl)
                    .build()
                    .toUriString();

            //URI 생성 방법 2
            UriComponents url2 = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("openapi.naver.com")
                    .path("/v1/util/shorturl")
                    .queryParam("url", originUrl)
                    .build();

            ResponseEntity<NaverUrlDto> response = WebClient.create(url)
                    .get()
                    .headers(httpHeaders -> {
                        httpHeaders.add("X-Naver-Client-Id", NAVER_CLIENT_ID);
                        httpHeaders.add("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
                    })
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                                                                                            .map(body -> new RuntimeException(body))
                    )
                    .onStatus(HttpStatus::is5xxServerError, clientResponse -> clientResponse.bodyToMono(String.class)
                                                                                            .map(body -> new RuntimeException(body))
                    )
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

    /* RestTemplate 사용해서 구현 */
    public ResponseEntity<NaverUrlDto> requestShortUrlv1(String originUrl){
        try{
            URI url = UriComponentsBuilder.fromUriString(NAVER_SHORT_URL_ENDPOINT)
                    .queryParam("url", originUrl)
                    .encode()
                    .build()
                    .toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("X-Naver-Client-Id", NAVER_CLIENT_ID);
            httpHeaders.set("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);

            HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<NaverUrlDto> response = restTemplate.exchange(url, HttpMethod.GET,entity, NaverUrlDto.class);

            return response;

        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
