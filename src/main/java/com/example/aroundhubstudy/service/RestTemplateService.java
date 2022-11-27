package com.example.aroundhubstudy.service;

import com.example.aroundhubstudy.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class RestTemplateService {
    public String getAroundHub(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/around-hub")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        log.info("status code: {}", response.getStatusCode());
        log.info("body : {}", response.getBody());

        return response.getBody();
    }

    public String getName(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/name")
                .queryParam("name", "flature")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        log.info("status code: {}",response.getStatusCode());
        log.info("body: {}", response.getBody());

        return response.getBody();
    }

    public ResponseEntity<MemberDTO> postDto(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/member")
                .queryParam("name", "flature")
                .queryParam("email", "lutae2000@gmail.com")
                .queryParam("organization", "study for spring")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("flat!!");
        memberDTO.setEmail("abc@aaa.com");
        memberDTO.setOrganization("Around Hub Studio!!");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> response = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        log.info("status code: {}",response.getStatusCode());
        log.info("body: {}", response.getBody());

        return response;
    }

    public ResponseEntity<MemberDTO> addHeader(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("flat!!");
        memberDTO.setEmail("abc@aaa.com");
        memberDTO.setOrganization("Around Hub Studio!!");

        RequestEntity<MemberDTO> requestEntity = RequestEntity.post(uri)
                .header("around-header", "studio")
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> response = restTemplate.postForEntity(uri, requestEntity, MemberDTO.class);

        log.info("status code: {}",response.getStatusCode());
        log.info("body: {}", response.getBody());

        return response;

    }
}
