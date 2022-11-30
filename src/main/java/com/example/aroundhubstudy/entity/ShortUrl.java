package com.example.aroundhubstudy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String hash;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false, unique = true)
    private String orgUrl;
}
