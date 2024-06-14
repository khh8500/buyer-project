package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"product", "user"})
@NoArgsConstructor
@Data
@Table(name = "order_tb")
@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer buyQty;

    @Column
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Order(Integer id, User user, Product product, Integer buyQty, String status, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.buyQty = buyQty;
        this.status = status;
        this.createdAt = createdAt;
    }


}
