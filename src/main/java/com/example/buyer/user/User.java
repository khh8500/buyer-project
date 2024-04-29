package com.example.buyer.user;

import com.example.buyer.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "user_tb", schema = "store")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 10, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String userId, String password, String phone, String address, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;

    }
}