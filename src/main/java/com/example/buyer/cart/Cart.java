package com.example.buyer.cart;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "cart_tb")
@Entity
public class Cart {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private Integer productId;

    private Integer userId;

    private Integer buyQty;

    private Integer price;



    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Cart(Integer id, Integer productId, Integer userId, Integer buyQty, Integer price, LocalDateTime createdAt) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.buyQty = buyQty;
        this.price = price;
        this.createdAt = createdAt;
    }
}
