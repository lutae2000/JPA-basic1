package com.example.aroundhubstudy.repository;

import com.example.aroundhubstudy.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, String> {
    void deleteByOrgUrl(String originUrl);

    void findByOrgUrl(String originUrl);
}
