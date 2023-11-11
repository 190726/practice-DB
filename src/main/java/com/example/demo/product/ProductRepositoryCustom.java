package com.example.demo.product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Products> findByName(String name);
}
