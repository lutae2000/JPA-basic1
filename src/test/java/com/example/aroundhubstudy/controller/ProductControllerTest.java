package com.example.aroundhubstudy.controller;

import com.example.aroundhubstudy.dto.ProductDto;
import com.example.aroundhubstudy.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{
        //given: Mock 객체가 특정 상황에서 해야하는 행위를 정의
        given(productService.getProduct("12345")).willReturn(
                new ProductDto("12345", "apple pencil", 50000, 2000));

        //andExpect: 기대하는 값이 나왔는지 체크
        String productId = "12345";
        mockMvc.perform(
                get("/api/v1/product-api/product/" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

        //then 결과
        verify(productService).getProduct("12345");
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception{

        ProductDto givenProductDto = new ProductDto();
        givenProductDto.setProductId("54321");
        givenProductDto.setProductName("apple pencil2");
        givenProductDto.setProductPrice(20000);
        givenProductDto.setProductStock(30);

        //Mock 객체에서 특정 메소드가 실행되는 경우 실제 return을 줄 수 없기에 가정 사항
        given(productService.saveProduct(givenProductDto)).willReturn(
                new ProductDto("54321", "apple pencil", 20000, 30));

        ProductDto productDto = ProductDto.builder()
                                        .productId("54321")
                                        .productName("apple pencil2")
                                        .productPrice(20000)
                                        .productStock(30)
                                        .build();
        //when
        String json = new ObjectMapper().writeValueAsString(productDto);
        mockMvc.perform(
                post("/api/v1/product-api/product")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());
        //then
        verify(productService).saveProduct(givenProductDto);
    }
}
