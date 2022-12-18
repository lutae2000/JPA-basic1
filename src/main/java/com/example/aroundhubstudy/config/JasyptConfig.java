package com.example.aroundhubstudy.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * application properties를 암호화 하는데 사용
 * 사용법: https://github.com/ulisesbocchio/jasypt-spring-boot
 */
@Configuration
public class JasyptConfig {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){
        String password = "around_hub_studio";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES"); //default: PBEWithMD5AndDES
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");   //default : SunJCE
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");    //default: org.jasypt.salt.RandomSaltGenerator
        config.setStringOutputType("base64");   //default: base64

        encryptor.setConfig(config);
        return encryptor;
    }

}
