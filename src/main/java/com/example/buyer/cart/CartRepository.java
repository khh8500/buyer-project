package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartRepository {

    private final EntityManager em;

    // 장바구니 담기
    public void saveCart(CartRequest.SaveDTO reqDTO, int sessionUserId){

        String q = """
                insert into cart_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())
                """;

        Query query = em.createNativeQuery(q, Cart.class);

        query.setParameter(1, sessionUserId);
        query.setParameter(2, reqDTO.getProductId());
        query.setParameter(3, reqDTO.getBuyQty());

        query.executeUpdate();
    }

//    public Cart findById(Integer id){
//
//        String q = """
//                select c from Cart c where c.id=:id
//                """;
//
//        Query query = em.createQuery(q, Cart.class);
//        query.setParameter("id", id);
//
//        return (Cart) query.getSingleResult();
//    }

    // 장바구니 조회
    public List<CartResponse.SaveDTO> findAll(){

        String q = """
                select c from Cart c order by c.id desc
                """;

        Query query = em.createQuery(q, Cart.class);

        return query.getResultList();
    }

}
