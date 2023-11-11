package com.example.demo.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/query")
    List<Products> queryDslTest(@RequestParam String name){
        System.out.println(name);
        return productRepository.findByName(name);
    }

    @GetMapping("/products")
    Products one(){
        return productRepository.findById(1L).get();
    }
}