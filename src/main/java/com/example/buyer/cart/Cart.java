package com.example.buyer.cart;

import com.example.buyer.product.Product;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer userId;

    private Integer buyQty;

    private boolean status; // false(0) 선택x, true(1) 선택o

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Cart(Integer id, Product product, Integer userId, Integer buyQty, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.userId = userId;
        this.buyQty = buyQty;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", product=" + product.getName() + product.getPrice() +// 또는 다른 원하는 필드
                ", userId=" + userId +
                ", buyQty=" + buyQty +
                ", createdAt=" + createdAt +
                '}';
    }
}
