package com.example.aroundhubstudy.controller;

import com.example.aroundhubstudy.dto.MemberDTO;
import com.example.aroundhubstudy.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {

    @Autowired
    RestTemplateService restTemplateService;

    public String getAroundHub(){
        return restTemplateService.getAroundHub();
    }

    @GetMapping("/name")
    public String getName(){
        return restTemplateService.getName();
    }

    @GetMapping("/dto")
    public ResponseEntity<MemberDTO> postdto(){
        return restTemplateService.postDto();
    }

    @GetMapping("/add-header")
    public ResponseEntity<MemberDTO> addHeader(){
        return restTemplateService.addHeader();
    }
}
