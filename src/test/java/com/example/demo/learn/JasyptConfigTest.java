package com.example.demo.learn;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.mvc.Controller;

import static org.junit.jupiter.api.Assertions.*;

class JasyptConfigTest {
    @Test
    void encryptor(){

       String key = "A41Dgnl8ltNoGUclo05fA89zFAyeY1db";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWITHMD5ANDDES");
        encryptor.setPassword(key);
        encryptor.setPoolSize(1);
        String encrypted = encryptor.encrypt("sixstar6");
        System.out.println(encrypted);
    }
}