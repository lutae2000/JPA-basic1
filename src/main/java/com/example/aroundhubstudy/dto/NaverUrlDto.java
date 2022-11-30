package com.example.aroundhubstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverUrlDto {
    private String message;
    private String result;
    private String code;

    @Data
    public static class Result {
        private String hash;
        private String url;
        private String orgUrl;
    }

}
