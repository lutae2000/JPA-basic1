package com.example.aroundhubstudy.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverUrlDto {
    private String message;
    private String code;
    private Result result;
    @Data
    public class Result {
        private String hash;
        private String url;
        private String orgUrl;
    }

}
