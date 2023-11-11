package com.example.demo.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    FOOD("food"),
    WOOD("wood"),
    ELECTRIC("electric");

    private final String text;

    public static boolean isStockProductType(Products p) {
        return p.getProductType() == WOOD ||
               p.getProductType() == ELECTRIC;
    }
}