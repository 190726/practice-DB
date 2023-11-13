package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("app.my")
public class AppProperties {
    private String name;
    private String ip;
    private int port;
    private Security security;

    public AppProperties(){}

    public AppProperties(String name, String ip, int port, Security security) {
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.security = security;
    }

    @Data
    @AllArgsConstructor
    public static class Security{
        private boolean enabled;
        private String token;
        private List<String> roles;
    }

}
