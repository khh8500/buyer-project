package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    // 장바구니 담기
    @Transactional
    public void saveCart(CartRequest.SaveDTO reqDTO, User sessionUser){
        cartRepository.saveCart(reqDTO, sessionUser.getId());
        System.out.println("service reqDTO = " + reqDTO);
    }

    // 장바구니 조회
    public List<CartResponse.SaveDTO> findAll(int sessionUserId){
        return cartRepository.findByUserId(sessionUserId);
    }

}
