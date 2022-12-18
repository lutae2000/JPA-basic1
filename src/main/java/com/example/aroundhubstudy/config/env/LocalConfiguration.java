package com.example.aroundhubstudy.config.env;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@Slf4j
public class LocalConfiguration {
    @Value("${properties.loading.message}")
    private String message;

    @Bean
    public String getMessage(){
        log.info("[getMessage] Local Configuration 입니다.");
        return message;
    }
}
