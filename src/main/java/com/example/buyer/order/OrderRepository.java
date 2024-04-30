package com.example.buyer.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    // 상품 재고 감소
    @Transactional
    public void decreaseQty(String productId, int newQty) {

        Query query = em.createNativeQuery("update product_tb set qty = qty - ? where product_id = ?;");

            query.setParameter(1, newQty);
            query.setParameter(2, productId);

            query.executeUpdate();

    }
}