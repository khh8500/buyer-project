package com.example.buyer.cart;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
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


    // 장바구니 중복 체크 및 수량 업데이트해서 담기
    public Cart updateCartItemQty(CartRequest.SaveDTO reqDTO, Integer sessionUserId) {

        // 중복 체크 쿼리
        String q = """
                select c from Cart c
                where c.product.id = :productId
                and c.userId = :userId
                """;

        Query query = em.createQuery(q, Cart.class);

        query.setParameter("productId", reqDTO.getProductId());
        query.setParameter("userId", sessionUserId);

        List<Cart> cartItems = query.getResultList(); // 중복된 장바구니 항목들 가져오기

        Cart cartItem = null;
        try {
            cartItem = (Cart) query.getSingleResult();
            // 장바구니에 이미 있는 상품인 경우 수량을 업데이트
            cartItem.setBuyQty(cartItem.getBuyQty() + reqDTO.getBuyQty());
            em.merge(cartItem); // 업데이트된 장바구니 항목 저장
        } catch (NoResultException e) {
            // 장바구니에 없는 상품인 경우 새로 등록
            // 새로운 Cart 객체 생성 및 설정
            Product product = em.find(Product.class, reqDTO.getProductId());
            User user = em.find(User.class, sessionUserId);
            cartItem = Cart.builder()
                    .product(product)
                    .userId(sessionUserId)
                    .buyQty(reqDTO.getBuyQty())
                    .build();
            em.persist(cartItem); // 새로운 장바구니 항목 저장
        }

        return cartItem;
    }

    // 장바구니 담기
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
