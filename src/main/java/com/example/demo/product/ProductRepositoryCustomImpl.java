package com.example.demo.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.product.QProducts.products;
import static com.example.demo.product.QStock.stock;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Products> findByName(String name) {
        return jpaQueryFactory.selectFrom(products)
                .where(products.productName.eq(name))
                .join(products.stock, stock)
                .fetchJoin()
                .fetch();
    }
}
