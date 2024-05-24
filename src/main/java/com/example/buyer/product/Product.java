package com.example.buyer.product;

import com.example.buyer.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data // getter, setter, toString
@NoArgsConstructor
@Table(name = "product_tb")
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true, length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer qty;

    @Column
    private String pic;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Product(Integer id, String pic, String name, Integer price, Integer qty, LocalDateTime createdAt) {
        this.id = id;
        this.pic = pic;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
    }

}