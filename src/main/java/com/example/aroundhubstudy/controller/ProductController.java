package com.example.aroundhubstudy.controller;

import com.example.aroundhubstudy.dto.ProductDto;

import com.example.aroundhubstudy.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto response = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> productInfo(@PathVariable String productId){
        ProductDto response = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}