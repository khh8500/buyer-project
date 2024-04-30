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
    public void decreaseQty(String productId, int buyQty) {

        Query query = em.createQuery("update Product p set p.qty = p.qty - :buyQty where p.id = :productId");

            query.setParameter("buyQty", buyQty);
            query.setParameter("productId", productId);

            query.executeUpdate();

    }
}