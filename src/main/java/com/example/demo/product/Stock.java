package com.example.demo.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private Long id;
    private String productId;
    private int amount;
    private LocalDateTime updateTime;

    public Stock(String productId, int amount, LocalDateTime updateTime) {
        this.productId = productId;
        this.amount = amount;
        this.updateTime = updateTime;
    }

    public void outbound(int amount) {
        this.amount -= amount;
    }
}
