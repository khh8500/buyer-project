package com.example.buyer.order;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;

    // 주문하기(구매하기)
    @PostMapping("/order")
    public String order(){
        return "redirect:/order/order-form";
    }

    // 주문 취소하기
    @PostMapping("/order/{id}/delete")
    public String delete(){
        return "redirect:/";
    }

    // 주문 상세보기
    @GetMapping("/order/{id}")
    public String detail(){
        return "order/detail";
    }

    // 주문 목록보기
    @GetMapping("/order/list")
    public String list(){
        return "order/list";
    }
}
