package com.example.aroundhubstudy.service;

import com.example.aroundhubstudy.dto.ProductDto;
import com.example.aroundhubstudy.entity.Product;
import com.example.aroundhubstudy.repository.ProductRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service

public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public ProductDto saveProduct(ProductDto productDto){
        Product savedProduct = productRepository.save(productDto.toEntity());
        ProductDto resProduct = new ProductDto(savedProduct.getProductId(), savedProduct.getProductName(), savedProduct.getProductPrice(), savedProduct.getProductStock());
        return resProduct;
    }

    public ProductDto getProduct(String productId){
        Optional<Product> resProduct = productRepository.findById(productId);
        if(resProduct.isPresent()){
            return resProduct.get().toProductDto();
        }
        return null;
    }
}
