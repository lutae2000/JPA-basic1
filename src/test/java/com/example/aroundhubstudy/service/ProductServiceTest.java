package com.example.aroundhubstudy.service;

import com.example.aroundhubstudy.dto.ProductDto;
import com.example.aroundhubstudy.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@Import({ProductService.class})
public class ProductServiceTest {

    @MockBean
    @Autowired
    ProductService productService;

    @Test
    public void getProductTest(){
        Mockito.when(productService.getProduct("123"))
                .thenReturn(new Product("123", "pencil", 3000, 30).toProductDto());

        ProductDto productDto = productService.getProduct("123");
        Assertions.assertEquals(productDto.getProductId(), "123");
        Assertions.assertEquals(productDto.getProductName(), "pencil");
        Assertions.assertEquals(productDto.getProductPrice(), 3000);
        Assertions.assertEquals(productDto.getProductStock(), 30);

        verify(productService).getProduct("123");
    }

    @Test
    public void saveProductTest(){

        ProductDto givenProductDto = new ProductDto();
        givenProductDto.setProductId("54321");
        givenProductDto.setProductName("pencil2");
        givenProductDto.setProductPrice(20000);
        givenProductDto.setProductStock(30);

        Mockito.when(productService.saveProduct(givenProductDto)).thenReturn(
                new ProductDto("54321", "pencil2", 20000, 30)
        );

        ProductDto productDto = productService.saveProduct(givenProductDto);
        Assertions.assertEquals(productDto.getProductId(),"54321");
        Assertions.assertEquals(productDto.getProductName(),"pencil2");
        Assertions.assertEquals(productDto.getProductPrice(),20000);
        Assertions.assertEquals(productDto.getProductStock(),30);

        verify(productService).saveProduct(givenProductDto);

    }
}
