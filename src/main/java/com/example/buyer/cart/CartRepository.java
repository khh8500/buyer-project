package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CartRepository {

    private final EntityManager em;

    public void saveCart(CartRequest.SaveDTO reqDTO, Integer sessionUserId){

        String q = """
                insert into cart_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())
                """;

        Query query = em.createNativeQuery(q, Cart.class);

        query.setParameter(1, sessionUserId);
        query.setParameter(2, reqDTO.getProductId());
        query.setParameter(3, reqDTO.getBuyQty());

        query.executeUpdate();
    }

}
