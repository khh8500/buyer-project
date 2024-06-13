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
    public String buyCart(List<Integer> cartItemIds) {
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 장바구니 서비스 호출
        orderService.buyCart(cartItemIds, sessionUser);

        return "order/order-form";
    }

    // 구매하기 폼
    @PostMapping("/order-form")
    public String orderForm(OrderRequest.SaveDTO reqDTO, HttpServletRequest request) {

        //User sessionUser = (User) session.getAttribute("sessionUser");

        // 상품 상세 정보 가져오기
        ProductResponse.DetailDTO product = productService.findById(reqDTO.getProductId());
        // 주문 상세 정보 조회
        OrderRequest.SaveDTO orderDTO = OrderRequest.SaveDTO.builder()
                                                .productId(reqDTO.getProductId())
                                                .buyQty(reqDTO.getBuyQty())
                                                .build();

        request.setAttribute("product", product);
        request.setAttribute("orderDTO", orderDTO);

        return "order/order-form";
    }

    // 주문하기(구매하기)   //save
    @GetMapping("/order")
    public String saveOrder(OrderRequest.SaveDTO reqDTO, HttpServletRequest request) {
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 상품 상세 정보 가져오기
        ProductResponse.DetailDTO product = productService.findById(reqDTO.getProductId());

        // 주문 저장
        orderService.saveOrder(reqDTO, sessionUser);

        request.setAttribute("product", product);

        return "order/list";
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

        // 모델에 주문 목록 추가
        request.setAttribute("orderList", orderList);

        return "order/list";
    }

}
