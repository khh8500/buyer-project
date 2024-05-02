package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRequest;
import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    //주문하기(구매하기)
    public void save(OrderRequest.SaveDTO reqDTO) {
        // 주문 엔티티 생성
        Order order = reqDTO.toEntity();

        // 엔티티를 영속화하여 데이터베이스에 저장
        em.persist(order);

    }

    // 주문하기(구매하기)
//    @Transactional
//    public void order(OrderRequest.OrderDTO reqDTO) {
//        Query query = em.createNativeQuery(
//                "insert into order_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())"
//        );
//
//        query.setParameter(1, reqDTO.getUserId());
//        query.setParameter(2, reqDTO.getProductId());
//        query.setParameter(3, reqDTO.getBuyQty());
//
//        query.executeUpdate();
//    }

    //상품 재고 감소
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
    public User findUserById(Integer userId) {

        String q = """
                select u from User u where u.id = :id
                """;
        Query query = em.createQuery(q);
        query.setParameter("id", userId);
        User user = (User) query.getSingleResult();

        return user;
    }

    // 주문 정보 가져오기
//    public Product getOrderProduct(ProductRequest.ProductDTO reqDTO) {
//
//        String q = """
//                select p from Product p
//                where p.id = :id
//                """;
//        Query query = em.createQuery(q, Product.class);
//
//        query.setParameter("id", reqDTO.getProductId());
//
//        Product product = (Product) query.getSingleResult();
//
//        return product;
//    }


    // 주문 목록 보기
    public List<Order> getOrderListByUserId(Integer userId) {

        String q = """
                select o from Order o where o.user.id = :userId
                """;
        Query query = em.createQuery(q, Order.class);

        query.setParameter("userId", userId);

        return query.getResultList();
    }

}