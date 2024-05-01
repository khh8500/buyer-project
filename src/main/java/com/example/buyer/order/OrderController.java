package com.example.buyer.order;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
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
    public String order(OrderRequest.OrderDTO reqDTO){
        System.out.println("!!!!!" + reqDTO);
        orderService.order(reqDTO);

        return "order/order-form";
    }

    // 주문하기(구매하기) 폼
    @GetMapping("/order/order-form")
    public String orderForm(HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

//        OrderRequest.OrderDTO orderDTO = new OrderRequest.OrderDTO();
//        request.setAttribute("orderDTO", orderDTO);
//        System.out.println(orderDTO);
        return "order/order-form";
    }

    // 주문 취소하기
    @PostMapping("/order/{id}/delete")
    public String delete(){
        return "redirect:/";
    }


    // 주문 목록보기
    @GetMapping("/order/list")
    public String list(){
        return "order/list";
    }
}
