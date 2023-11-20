package com.example.demo.hello;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/csrf-token")

    public CsrfToken token(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/hello")
    public String hello(){
        System.out.println("---hello---");
        return "hello";
    }

    @PostMapping("/hello")
    public void helloPost(@RequestBody Hello hello){
        System.out.println("---hello post---" + hello);
    }
}
record  Hello(String name, String message){}
