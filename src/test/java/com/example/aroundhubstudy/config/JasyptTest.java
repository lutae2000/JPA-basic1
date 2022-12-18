package com.example.aroundhubstudy.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    @Test
    void encryptTest(){
        String id = "webdb";
        String password = "p@ssword123";

        System.out.println("id: " + jasyptEncoding(id));
        System.out.println("password: " + jasyptEncoding(password));
    }

    public String jasyptEncoding(String value){
        String key = "around_hub_studio";
        StandardPBEStringEncryptor pbeStringEncryptor = new StandardPBEStringEncryptor();
        pbeStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        pbeStringEncryptor.setPassword(key);
        return pbeStringEncryptor.encrypt(value);
    }
}