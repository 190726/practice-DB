package com.example.demo.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class ProductTest {

    @DisplayName("재고 대상 상품 여부 판단")
    @Test
    void hasStockProduct() {

        Products product1 = Products.create("001","tv", ProductType.ELECTRIC);
        Products product2 = Products.create("002","radio", ProductType.ELECTRIC);
        Products product3 = Products.create("003","apple", ProductType.FOOD);

        List<Products> products = List.of(product1, product2, product3);
        List<Products> filtered = products.stream().filter(ProductType::isStockProductType).collect(Collectors.toList());
        assertThat(filtered).hasSize(2);
        assertThat(filtered).extracting("productId", "productName")
                .contains(
                        tuple("001", "tv"),
                        tuple("002", "radio")
                );
    }

    @DisplayName("제고 차감 테스트")
    @Test
    void stockRelease(){
        Stock stock = new Stock("001", 100, LocalDateTime.now());
        Products product = Products.create("001","tv", ProductType.ELECTRIC, stock);
        product.release(10);

        assertThat(product.getStock().getAmount()).isEqualTo(90);

    }
}