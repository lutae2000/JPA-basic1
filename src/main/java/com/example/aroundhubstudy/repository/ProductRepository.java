package com.example.aroundhubstudy.repository;

import com.example.aroundhubstudy.entity.Product;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Product> findByProductPriceGreaterThan(Integer price, Pageable pageable);

    /* @Query 사용 예제 */

    @Query("SELECT P FROM Product P WHERE P.productPrice > 2000")
    List<Product> findByProductPriceBasic();

    @Query(value = "SELECT * FROM product  WHERE product_price > 2000", nativeQuery = true)
    List<Product> findByProductPriceBasicNativeQuery();

    @Query("SELECT p FROM Product p WHERE p.productPrice> ?1")
    List<Product> findByProductPriceWithParameter(Integer price);

    @Query("SELECT p FROM Product p where p.productPrice > :price")
    List<Product> findByProductPriceWithParameterNaming(Integer price);

    @Query("SELECT p FROM Product p WHERE p.productPrice > :pri")
    List<Product> findByProductPriceWithParameterNaming2(@Param("pri") Integer price);

    @Query(value = "SELECT * FROM product WHERE product_price > :price",
    countQuery = "SELECT count(*) FROM Product WHERE product_price > ?1",
    nativeQuery = true)
    List<Product> findByProductPriceWithParameterPaging(Integer price, Pageable pageable);
}
