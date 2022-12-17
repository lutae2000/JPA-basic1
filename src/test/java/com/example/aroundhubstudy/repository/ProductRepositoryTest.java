package com.example.aroundhubstudy.repository;

import com.example.aroundhubstudy.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void GenerateData(){
        int count =1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 4000));
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 5000));
        productRepository.save(getProduct(Integer.toString(count), count++, 5000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 6000, 4000));
        productRepository.save(getProduct(Integer.toString(count), count++, 7000, 5000));
    }

    private Product getProduct(String id, int nameNumber, int price, int stock){
        return new Product(id, "상품" + nameNumber, price, stock);
    }

    @Test
    void findTest(){
        List<Product> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> foundEntities = productRepository.findByProductName("상품4");

        for (Product product : foundEntities) {
            System.out.println(product.toString());
        }

        List<Product> queryEntities = productRepository.queryByProductName("상품5");

        for (Product product : queryEntities) {
            System.out.println(product.toString());
        }
    }

    @Test
    @Transactional
    void deleteTest(){
        System.out.println("before : " + productRepository.count());
        productRepository.deleteByProductId("상품1");
        productRepository.deleteByProductId("상품5");
        System.out.println("after : " + productRepository.count());
    }

    @Test
    void topTest(){
        productRepository.save(getProduct("109", 123, 1500, 5000));
        productRepository.save(getProduct("101", 123, 2500, 5000));
        productRepository.save(getProduct("102", 123, 3500, 5000));
        productRepository.save(getProduct("103", 123, 4500, 5000));
        productRepository.save(getProduct("104", 123, 1000, 5000));
        productRepository.save(getProduct("105", 123, 2000, 5000));
        productRepository.save(getProduct("106", 123, 3000, 5000));
        productRepository.save(getProduct("107", 123, 4000, 5000));

        List<Product> findEntities = productRepository.findFirst5ByProductName("상품123");
        for(Product product : findEntities){
            System.out.println(product.toString());
        }

        List<Product> findEntities2 = productRepository.findTop3ByProductName("상품123");
        for(Product product : findEntities){
            System.out.println(product.toString());
        }
    }

    @Test
    void isEqualTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByProductIdIs("1"));
        System.out.println(productRepository.findByProductIdEquals("1"));
    }

    @Test
    void nonTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> findEntities = productRepository.findByProductIdNot("1");
        for (Product product : findEntities) {
            System.out.println(product.toString());
        }

        List<Product> findEntities2 = productRepository.findByProductIdIsNot("2");
        for (Product product : findEntities2) {
            System.out.println(product.toString());
        }
    }

    @Test
    void nullTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> findEntities = productRepository.findByProductStockIsNull();
        for(Product product : findEntities){
            System.out.println(product.toString());
        }

        List<Product> findEntities2 = productRepository.findByProductStockIsNotNull();
        for(Product product : findEntities2){
            System.out.println(product.toString());
        }
    }

    @Test
    void andTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findTopByProductIdAndProductName("1,", "상품1"));
    }

    @Test
    void greaterTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> findEntity = productRepository.findByProductPriceGreaterThan(5000);
        for(Product product : findEntity){
            System.out.println(product.toString());
        }
    }

    @Test
    void containString(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> findEntity = productRepository.findByProductNameContaining("상품1");
        for(Product product : findEntity){
            System.out.println(product.toString());
        }
    }

    @Test
    void orderByTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> findEntity = productRepository.findByProductNameContainingOrderByProductStockAsc("상품1");
        for (Product product : findEntity){
            System.out.println(product.toString());
        }

        List<Product> findEntity2 = productRepository.findByProductNameContainingOrderByProductPriceAscProductStockDesc("상품2");
        for(Product product : findEntity2){
            System.out.println(product.toString());
        }
    }

    @Test
    void orderByParameterTest(){
        List<Product> findAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (Product product : findAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<Product> findEntity = productRepository.findByProductNameContaining("상품1", Sort.by(Sort.Order.asc("productPrice"), Sort.Order.asc("productStock")));
        for(Product product : findEntity){
            System.out.println(product.toString());
        }
    }


}
