package com.example.aroundhubstudy.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@RedisHash(value = "shortUrl", timeToLive = 60)
public class ShortUrlResponseDto implements Serializable {
    private static final long serialVersionUID = -214490344996507077L;

    @Id
    private String orgUrl;

    private String shortUrl;
}
