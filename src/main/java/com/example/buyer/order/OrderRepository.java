package com.example.buyer.order;

import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    // 주문하기(구매하기)
//    @Transactional
//    public void order(OrderRequest.OrderDTO reqDTO) {
//        // 주문 엔티티 생성
//        Order order = reqDTO.toEntity();
//
//        // 엔티티를 영속화하여 데이터베이스에 저장
//        em.persist(order);
//    }

    // 주문하기(구매하기)
    @Transactional
    public void order(OrderRequest.OrderDTO reqDTO) {
        Query query = em.createNativeQuery(
                "insert into order_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())"
        );

        query.setParameter(1, reqDTO.getUserId());
        query.setParameter(2, reqDTO.getProductId());
        query.setParameter(3, reqDTO.getBuyQty());

        query.executeUpdate();
    }

    //상품 재고 감소
    @Transactional
    public void updateQty(OrderRequest.OrderDTO reqDTO) {

        Query query = em.createQuery("update Product p set p.qty = p.qty - :buyQty where p.id = :productId");

            query.setParameter("buyQty", reqDTO.getBuyQty());
            query.setParameter("productId", reqDTO.getProductId());

            query.executeUpdate();
    }

    public User findUserById(OrderRequest.OrderDTO reqDTO) {

        Query query = em.createQuery("select u from User u where u.id = :id");

        query.setParameter("id", reqDTO.getUserId());

        return (User) query.getSingleResult();
    }

}