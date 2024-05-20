package com.example.buyer.cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartRepository {

    private final EntityManager em;

    // 장바구니 구매 상품 정보 조회
    public List<Cart> selectBuyCart(List<Integer> cartItemIds) {

        String q = """
                select c
                from Cart c
                join fetch c.product p
                where c.id in (:cartItemIds)
                """;

        return em.createQuery(q, Cart.class)
                .setParameter("cartItemIds", cartItemIds)
                .getResultList();
    }

    // 장바구니 삭제하기
    public void deleteCart(List<Integer> id) {

        String q = """
                delete from Cart c where c.id = :id
                """;

        Query query = em.createQuery(q);

        query.setParameter("id", id);

        query.executeUpdate();
    }

    // 장바구니 아이디 조회하기
//    public Cart findById(Integer cartId) {
//        try {
//            String q = """
//                    select c from Cart c where c.id = :cartId
//                    """;
//
//            Query query = em.createQuery(q, Cart.class);
//
//            query.setParameter("cartId", cartId);
//
//            Cart cart = (Cart) query.getSingleResult();
//            return cart;
//
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

    // 새로운 장바구니 항목 저장
    public void saveCart(CartRequest.SaveDTO reqDTO, Integer sessionUserId) {
        em.persist(reqDTO);
    }

    // 기존 장바구니 항목 업데이트
    public void updateCart(CartRequest.SaveDTO reqDTO) {
        em.merge(reqDTO);
    }

    // 장바구니 중복 체크
    public Cart findCart(Integer productId, Integer sessionUserId) {

        try {
            // 중복 체크 쿼리
            String q = """
                    select c from Cart c
                    where c.product.id = :productId
                    and c.userId = :userId
                    """;

            Query query = em.createQuery(q, Cart.class);

            query.setParameter("productId", productId);
            query.setParameter("userId", sessionUserId);

            Cart cart = (Cart) query.getSingleResult();
            return cart;

        } catch (NoResultException e) {
            return null;
        }
    }

    // 장바구니에 있으면 수량 업데이트
    public void updateBuyQty(CartRequest.SaveDTO reqDTO) {
        String q = """
                update Cart c set c.buyQty = c.buyQty + :buyQty where c.product.id = :productId
                """;

        Query query = em.createQuery(q, Cart.class);

        query.setParameter("buyQty", reqDTO.getBuyQty());
        query.setParameter("productId", reqDTO.getProductId());

        query.executeUpdate();
    }


//     장바구니 담기
//    public void saveCart(CartRequest.SaveDTO reqDTO, int sessionUserId){
//
//        String q = """
//                insert into cart_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())
//                """;
//
//        Query query = em.createNativeQuery(q, Cart.class);
//
//        query.setParameter(1, sessionUserId);
//        query.setParameter(2, reqDTO.getProductId());
//        query.setParameter(3, reqDTO.getBuyQty());
//
//        query.executeUpdate();
//    }

    // 장바구니 조회
    public List<CartResponse.SaveDTO> findByUserId(int sessionUserId) {

        String q = """
                select c from Cart c where c.userId=:id order by c.id desc
                """;

        Query query = em.createQuery(q, Cart.class);
        query.setParameter("id", sessionUserId);

        return query.getResultList();
    }

}
