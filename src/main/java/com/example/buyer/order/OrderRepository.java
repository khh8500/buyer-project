package com.example.buyer.order;

import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    //주문하기(구매하기)
//    public void save(OrderRequest.SaveDTO reqDTO) {
//        // 주문 엔티티 생성
//        Order order = reqDTO.toEntity();
//
//        // 엔티티를 영속화하여 데이터베이스에 저장
//        em.persist(order);
//
//    }

    // 구매 상태 업데이트
    public void updateOrderStatus(Integer orderId, String status) {
        // 주문 상태 업데이트 쿼리
        String q = """
                UPDATE Order o
                SET o.status = :status
                WHERE o.id = :orderId
                """;
        Query query = em.createQuery(q);

        query.setParameter("orderId", orderId);
        query.setParameter("status", status);

        query.executeUpdate();
    }

    // 구매 취소하기
    public void orderCancel(OrderRequest.CancelDTO reqDTO, Integer orderId) {

        String q = """
                update product_tb p
                join order_tb o on p.id = o.product_id
                set p.qty = p.qty + o.buy_qty
                where o.id = ?
                """;

        Query query = em.createNativeQuery(q);

        query.setParameter(1, orderId);

        query.executeUpdate();
    }

    // 구매 정보
    public Order findOrderById(Integer orderId) {

        String q = """
                select o from Order o where o.id = :orderId
                """;
        Query query = em.createQuery(q, Order.class);

        query.setParameter("orderId", orderId);

        return (Order) query.getSingleResult();
    }

    // 주문하기(구매하기)
    public void saveOrder(OrderRequest.SaveDTO reqDTO, int sessionUserId) {
        String q = """
                insert into order_tb (user_id, product_id, buy_qty, status, created_at) values (?,?,?,?,now())
                """;
        Query query = em.createNativeQuery(q);

        query.setParameter(1, sessionUserId);
        query.setParameter(2, reqDTO.getProductId());
        query.setParameter(3, reqDTO.getBuyQty());
        query.setParameter(4, reqDTO.getStatus());

        query.executeUpdate();
    }

    // 상품 재고 감소
    public void updateQty(Integer buyQty, Integer productId) {

        String q = """
                update Product p set p.qty = p.qty - :buyQty where p.id = :productId
                """;
        Query query = em.createQuery(q);

        query.setParameter("buyQty", buyQty);
        query.setParameter("productId", productId);

        query.executeUpdate();
    }

    // 유저 정보 가져오기
//    public User findUserById(Integer userId) {
//
//        String q = """
//                select u from User u where u.id = :id
//                """;
//        Query query = em.createQuery(q);
//        query.setParameter("id", userId);
//        User user = (User) query.getSingleResult();
//
//        return user;
//    }

    // 주문 목록 보기
    public List<Order> getOrderListByUserId(Integer userId) {

        String q = """
                select o from Order o join fetch o.user u where u.id = :userId
                """;
        Query query = em.createQuery(q, Order.class);

        query.setParameter("userId", userId);

        return query.getResultList();
    }

}