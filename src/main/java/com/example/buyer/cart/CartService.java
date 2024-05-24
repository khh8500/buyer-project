package com.example.buyer.cart;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final EntityManager em;

    // 장바구니 삭제하기
    @Transactional
    public void deleteCart(List<Integer> id) {
        cartRepository.deleteCart(id);
    }

    // 장바구니 중복 체크 및 수량 업데이트해서 담기
    @Transactional
    public void updateCartItemQty(CartRequest.SaveDTO reqDTO, Integer sessionUserId) {
        // 조회
        Cart cart = cartRepository.findCart(reqDTO.getProductId().getId(), sessionUserId);

        if (cart != null) {
            // 기존 장바구니 항목이 있을 경우 수량 업데이트
            cartRepository.updateCart(reqDTO);
        } else {
            // 새 장바구니 항목 추가
            cartRepository.saveCart(reqDTO, sessionUserId);
        }
    }

// 장바구니 담기
//    @Transactional
//    public void saveCart(CartRequest.SaveDTO reqDTO, User sessionUser){
//        cartRepository.saveCart(reqDTO, sessionUser.getId());
//        System.out.println("service reqDTO = " + reqDTO);
//    }

    // 장바구니 조회
    public List<Cart> findAll(int sessionUserId) {
        return cartRepository.findByUserId(sessionUserId);
    }

}
