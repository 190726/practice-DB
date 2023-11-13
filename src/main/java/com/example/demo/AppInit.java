package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppInit implements CommandLineRunner {

    @Value("${my.username}")
    private String name;
    @Value("${my.password}")
    private String password;

    @Autowired AppProperties appProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("user name is {} , password is {}", name, password);
        System.out.println(appProperties);
    }
}
