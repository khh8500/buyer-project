package com.example.buyer.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;
}
