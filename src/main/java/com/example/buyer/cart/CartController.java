package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;
    private final HttpSession session;

    // 장바구니 담기
    @PostMapping("/cart")
    public String cart(CartRequest.SaveDTO reqDTO, HttpSession session){

        User sessionUser = (User) session.getAttribute("sessionUser");

        cartService.saveCart(reqDTO, sessionUser);

        return "cart/cart-form";
    }

    @GetMapping("/cart-form")
    public String cartForm(CartRequest.SaveDTO reqDTO, HttpServletRequest request){

        User sessionUser = (User) session.getAttribute("sessionUser");

        cartService.saveCart(reqDTO, sessionUser);
        request.setAttribute("reqDTO", reqDTO);

        return "cart/cart-form";
    }
}
