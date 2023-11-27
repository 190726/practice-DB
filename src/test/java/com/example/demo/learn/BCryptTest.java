package com.example.demo.learn;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

    @Test
    void encrypt(){
        String encode = new BCryptPasswordEncoder().encode("1234");
        System.out.println(encode);
    }
}
