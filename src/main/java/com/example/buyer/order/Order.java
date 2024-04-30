package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "order_tb")
@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Integer buyQty;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Order(Integer id, User user, Product product, Integer buyQty, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.buyQty = buyQty;
        this.createdAt = createdAt;
    }
}
