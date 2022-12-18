package com.example.aroundhubstudy.repository;

import com.example.aroundhubstudy.dto.NaverUrlDto;
import com.example.aroundhubstudy.dto.ShortUrlResponseDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String> {

}
