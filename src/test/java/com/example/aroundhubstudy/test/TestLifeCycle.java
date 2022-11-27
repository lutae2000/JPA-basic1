package com.example.aroundhubstudy.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {
    @BeforeAll
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation 호출 ##");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("## afterAll Annotation 호출 ##");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("## beforeEach Annotation 호출 ##");
    }

    @AfterEach
    void afterEach(){
        System.out.println("## afterEach Annotation 호출 ##");
    }

    @Test
    void test1(){
        System.out.println("## test1 시작 ##");
    }

    @Test
    @DisplayName("Test case 2")
    void test2(){
        System.out.println("## test2 시작 ##");
    }

    @Test
    @Disabled
    void test3(){
        System.out.println("## test3 시작 ##");
    }
}
