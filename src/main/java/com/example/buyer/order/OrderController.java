package com.example.buyer.order;

import com.example.buyer.cart.Cart;
import com.example.buyer.cart.CartResponse;
import com.example.buyer.cart.CartService;
import com.example.buyer.product.Product;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.product.ProductService;
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
    private final ProductService productService;
    private final CartService cartService;

    // 구매하기 폼
    @GetMapping("/order-form")
    public String orderForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        ProductResponse.DetailDTO product = productService.findById(sessionUser.getId());
        List<Cart> cartList = cartService.findAll(sessionUser.getId());
        System.out.println("product = " + product);
        System.out.println("cartList = " + cartList);
        request.setAttribute("product", product);
        request.setAttribute("cartList", cartList);

        return "order/order-form";
    }

    // 구매 취소하기
    @PostMapping("/order/cancel")
    public String orderCancel(OrderRequest.CancelDTO reqDTO, Integer orderId) {

        orderService.orderCancel(reqDTO, orderId);

        return "redirect:/order/list";
    }

//    // 장바구니 구매 폼
//    @GetMapping("/order/cart-form")
//    public String cartForm(HttpServletRequest request) {
//
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        OrderRequest.SaveDTO reqDTO = new OrderRequest.SaveDTO();
//        orderService.saveOrder(reqDTO, sessionUser);
//
//        request.setAttribute("reqDTO", reqDTO);
//
//        return "redirect:/order/list";
//    }

    // 장바구니 구매하기
    @PostMapping("/cart/order")
    public String buyCart(List<Integer> cartItemIds, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 장바구니 서비스 호출
        orderService.buyCart(cartItemIds, sessionUser);

        return "order/order-form";
    }

    // 주문하기(구매하기)   //save
    @PostMapping("/order")
    public String saveOrder(List<Integer> cartItemIds, OrderRequest.SaveDTO reqDTO, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("!!!!!" + reqDTO);

        if (cartItemIds != null && !cartItemIds.isEmpty()) {
            // 장바구니를 통한 주문인 경우
            orderService.buyCart(cartItemIds, sessionUser);
        } else {
            // 바로 주문인 경우
            // 주문 정보에 사용자 ID 설정
            // reqDTO.setUserId(sessionUser.getId());
            // 주문 서비스 호출
            orderService.saveOrder(reqDTO, sessionUser);
        }
        return "order/order-form";
    }

//    // 주문하기(구매하기) 폼
//    @GetMapping("/order/order-form")
//    public String orderForm(HttpServletRequest request){
//        User sessionUser = (User) session.getAttribute("sessionUser");
//
//        // 새로운 주문 정보 생성
//        OrderRequest.SaveDTO reqDTO = new OrderRequest.SaveDTO();
//
//        // 주문 정보를 모델에 담아서 주문 폼 페이지로 전달
//        request.setAttribute("reqDTO", reqDTO);
//        return "order/order-form";
//    }

    // 주문 목록보기
    @GetMapping("/order/list")
    public String OrderList(HttpServletRequest request) {
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
