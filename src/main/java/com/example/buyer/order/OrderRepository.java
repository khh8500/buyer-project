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

    // 주문하기(구매하기)
    public void order(OrderRequest.OrderDTO reqDTO) {
        Query query = em.createNativeQuery(
                "insert into order_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())"
        );

        query.setParameter(1, reqDTO.getUserId());
        query.setParameter(2, reqDTO.getProductId());
        query.setParameter(3, reqDTO.getBuyQty());

        query.executeUpdate();
    }

    // 상품 재고 감소
    @Transactional
    public void decreaseQty(String productId, Integer buyQty) {

        Query query = em.createQuery("update Product p set p.qty = p.qty - :buyQty where p.id = :productId");

            query.setParameter("buyQty", buyQty);
            query.setParameter("productId", productId);

            query.executeUpdate();
    }

}