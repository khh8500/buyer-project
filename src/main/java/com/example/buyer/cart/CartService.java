package com.example.buyer.cart;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRepository;
import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final EntityManager em;
    private final ProductRepository productRepository;

    // 장바구니 삭제하기
    @Transactional
    public void deleteCart(List<Integer> id) {
        cartRepository.deleteCart(id);
    }

    // 장바구니 중복 체크 및 수량 업데이트해서 담기
    @Transactional
    public void updateCartItemQty(CartRequest.SaveDTO reqDTO, User sessionUser) {
        // 조회
        Cart cart = cartRepository.findCart(reqDTO.getProduct().getId(), sessionUser);

        if (cart != null) {
            // 기존 장바구니 항목이 있을 경우 수량 업데이트
            cartRepository.updateCart(reqDTO);
        } else {
            // 새 장바구니 항목 추가
            cartRepository.saveCart(reqDTO, sessionUser);
        }
    }

    // 장바구니 담기
    @Transactional
    public void saveCart(CartRequest.SaveDTO reqDTO, User sessionUser){

        Product product = productRepository.findById(reqDTO.getProduct().getId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setBuyQty(reqDTO.getBuyQty());
        cart.setUser(sessionUser);

        cartRepository.saveCart(reqDTO, sessionUser);

    }

    // 장바구니 조회
    public List<Cart> findAll(User sessionUser) {
        return cartRepository.findByUserId(sessionUser);
    }

}
