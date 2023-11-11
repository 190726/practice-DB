package com.example.demo.product;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired ProductRepository productRepository;

    @Autowired
    TestEntityManager testEntityManager;

    EntityManager em;

    @BeforeEach
    void init(){
        em = testEntityManager.getEntityManager();
    }

    @Test
    void querydsl(){
        Stock stock = new Stock("001", 100, LocalDateTime.now());
        Products product = Products.create("001","tv", ProductType.ELECTRIC, stock);
        productRepository.save(product);

        JPAQuery<Products> query = new JPAQuery<>(em);
        QProducts products = new QProducts("c");
        List<Products> fetch = query.from(products)
                .where(products.productName.eq("tv"))
                        .fetch();
        System.out.println(fetch);
    }

    @Test
    void create() {
        Stock stock = new Stock("001", 100, LocalDateTime.now());
        Products product = Products.create("001","tv", ProductType.ELECTRIC, stock);
        productRepository.save(product);

        //List<Products> all = productRepository.findAll();
        //System.out.println(all);

        Products findBy = productRepository.findById(1L).get();
        System.out.println(findBy.getStock().getAmount());
    }
}
