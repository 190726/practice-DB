package com.example.demo.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Entity
public class Products {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private  String productName;
    private  ProductType productType;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="stock_id")
    private  Stock stock;

    public Products(String productId, String productName, ProductType productType, Stock stock) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.stock = stock;
    }

    public static Products create(String productId, String productName, ProductType productType) {
        return new Products(productId, productName, productType, null);
    }

    public static Products create(String productId, String productName, ProductType productType, Stock stock) {
        return new Products(productId, productName, productType, stock);
    }

    public void release(int amount) {
        stock.outbound(amount);
    }
}