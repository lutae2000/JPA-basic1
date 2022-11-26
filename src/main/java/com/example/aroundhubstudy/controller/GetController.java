package com.example.aroundhubstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class GetController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
