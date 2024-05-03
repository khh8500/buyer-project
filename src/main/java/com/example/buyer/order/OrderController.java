package com.example.buyer.order;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;

    // 주문하기(구매하기)   //save
    @PostMapping("/order")
    public String saveOrder(OrderRequest.SaveDTO reqDTO, HttpSession session){
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("!!!!!" + reqDTO);

        // 주문 정보에 사용자 ID 설정
        //orderService.findUserById(sessionUser.getId());

        // 주문 서비스 호출
        orderService.save(reqDTO);
        System.out.println("????" + reqDTO);
        return "redirect:/order/order-form";
    }

    // 주문하기(구매하기) 폼
    @GetMapping("/order/order-form")
    public String orderForm(OrderRequest.SaveDTO reqDTO, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 주문 서비스를 통해 사용자 정보 가져오기
        //User user = orderService.findUserById(sessionUser.getId());
        orderService.save(reqDTO);

        // 주문 정보를 모델에 담아서 주문 폼 페이지로 전달
        request.setAttribute("reqDTO", reqDTO);
        return "order/order-form";
    }

    // 주문 취소하기
    @PostMapping("/order/{id}/delete")
    public String delete(){
        return "redirect:/";
    }


    // 주문 목록보기
    @GetMapping("/order/list")
    public String OrderList(HttpServletRequest request){
        // 세션에서 현재 사용자의 ID 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 주문 목록 가져오기
        List<OrderResponse.ListDTO> orderList = orderService.getOrderListByUserId(sessionUser.getId());
        System.out.println("orderList = " + orderList);

        // 모델에 주문 목록 추가
        request.setAttribute("orderList", orderList);

        return "order/list";
    }

}
