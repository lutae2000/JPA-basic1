package com.example.aroundhubstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AroundHubStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AroundHubStudyApplication.class, args);
    }

}
