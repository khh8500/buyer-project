package com.example.buyer.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public String cart(){
        return "cart/cart-form";
    }

    @GetMapping("/cart-form")
    public String cartForm(){
        return "cart/cart-form";
    }
}
