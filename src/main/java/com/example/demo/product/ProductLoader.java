package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductLoader implements CommandLineRunner {

    @Autowired ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {

        Stock stock1 = new Stock("001", 100, LocalDateTime.now());
        Stock stock2 = new Stock("002", 70, LocalDateTime.now());
        Stock stock3 = new Stock("003", 50, LocalDateTime.now());
        Products product1 = Products.create("001","tv", ProductType.ELECTRIC, stock1);
        Products product2 = Products.create("002","radio", ProductType.ELECTRIC, stock2);
        Products product3 = Products.create("003","apple", ProductType.FOOD, stock3);

        productRepository.saveAll(List.of(product1, product2, product3));
    }
}
