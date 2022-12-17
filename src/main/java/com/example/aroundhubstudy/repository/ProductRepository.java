package com.example.aroundhubstudy.repository;

import com.example.aroundhubstudy.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductName(String name);
    List<Product> queryByProductName(String name);


    boolean existsByProductName(String name);

    long countByProductName(String name);

    void deleteByProductId(String id);
    long removeByProductId(String id);

    List<Product> findFirst5ByProductName(String name);
    List<Product> findTop3ByProductName(String name);

    Product findByProductIdIs(String id);
    Product findByProductIdEquals(String id);

    List<Product> findByProductIdNot(String id);
    List<Product> findByProductIdIsNot(String id);

    List<Product> findByProductStockIsNull();
    List<Product> findByProductStockIsNotNull();

    List<Product> findTopByProductIdAndProductName(String id, String name);

    List<Product> findByProductPriceGreaterThan(int price);

    List<Product> findByProductNameContaining(String name);

    List<Product> findByProductNameContainingOrderByProductStockAsc(String name);

    List<Product> findByProductNameContainingOrderByProductStockDesc(String name);

    List<Product> findByProductNameContainingOrderByProductPriceAscProductStockDesc(String name);

    List<Product> findByProductNameContaining(String name, Sort sort);
}
