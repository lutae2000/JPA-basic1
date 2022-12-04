package com.example.aroundhubstudy.service;

import com.example.aroundhubstudy.dto.NaverUrlDto;
import com.example.aroundhubstudy.entity.ShortUrl;

import com.example.aroundhubstudy.repository.ShortUrlRepository;
import io.netty.handler.codec.http.HttpScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Service
@Slf4j
public class NaverShortUrlService {

    @Autowired
    ShortUrlRepository shortUrlRepository;

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

    /**
     * 네이버 단축 URL 요청 및 응답 후 DB에 upsert
     * @param originUrl
     * @return ResponseEntity<NaverUrlDto>
     */
    public ResponseEntity<NaverUrlDto> generateShortUrl(String originUrl){
        log.info("[generate short url] request data: {}", originUrl);

        ResponseEntity<NaverUrlDto> responseEntity = requestShortUrlv2(originUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrl shortUrlEntity = new ShortUrl();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);
        shortUrlRepository.save(shortUrlEntity);

        return responseEntity;
    }

    public void deleteShortUrl(String originUrl){
        log.info("[delete short url] request data: {}", originUrl);

        shortUrlRepository.deleteByOrgUrl(originUrl);
    }


    public ResponseEntity<?> getShortUrl(String originUrl){
        log.info("[get short url from Naver] request data: {}", originUrl);
        Optional<ShortUrl> getShortUrl1 = Optional.ofNullable(shortUrlRepository.findByOrgUrl(originUrl));

        if(getShortUrl1.isPresent()){
            log.info("data exists!");

            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setOrgUrl(getShortUrl1.get().getOrgUrl());
            shortUrl.setUrl(getShortUrl1.get().getUrl());
            shortUrl.setHash(getShortUrl1.get().getHash());
            shortUrl.setCreatedAt(getShortUrl1.get().getCreatedAt());
            shortUrl.setUpdateAt(getShortUrl1.get().getUpdateAt());
            shortUrl.setId(getShortUrl1.get().getId());

            return ResponseEntity.status(HttpStatus.OK).body(shortUrl);
        } else {
            log.info("data doesn't exists!");
            ResponseEntity<NaverUrlDto> response = generateShortUrl(originUrl);

            return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
        }
    }

}
