package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;
    private final HttpSession session;

    // 장바구니 삭제하기
    @PostMapping("/cart/{id}/delete")
    public String cartDelete(@PathVariable List<Integer> id) {
        cartService.deleteCart(id);

        return "redirect:/cart-form";
    }

    // 장바구니 중복 체크 및 수량 업데이트해서 담기
    @PostMapping("/cart")
    public String cart(CartRequest.SaveDTO reqDTO) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser.getId();

        cartService.updateCartItemQty(reqDTO, sessionUserId);

        return "redirect:/cart-form";
    }

    // 장바구니 폼
    @GetMapping("/cart-form")
    public String cartForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        List<CartResponse.SaveDTO> cartList = cartService.findAll(sessionUser.getId());
        System.out.println("cartList = " + cartList);
        request.setAttribute("cartList", cartList);

        return "cart/cart-form";
    }
}
