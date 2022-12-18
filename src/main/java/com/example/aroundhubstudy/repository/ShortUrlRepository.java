package com.example.aroundhubstudy.repository;

import com.example.aroundhubstudy.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
    @Transactional
    ShortUrl deleteByOrgUrl(String originUrl);

    ShortUrl findByOrgUrl(String originUrl);
}
