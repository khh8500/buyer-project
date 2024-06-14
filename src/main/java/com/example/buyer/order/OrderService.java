package com.example.buyer.order;

import com.example.buyer.cart.Cart;
import com.example.buyer.cart.CartRepository;
import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRepository;
import com.example.buyer.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final EntityManager em;
    private final CartRepository cartRepository;

    // 장바구니 구매하기
    @Transactional
    public boolean buyCart(List<Integer> cartItemIds, User sessionUser) {
        // 장바구니에서 선택한 상품을 order로 이동
        List<Cart> cartItems = cartRepository.selectBuyCart(cartItemIds);
        for (Cart cartItem : cartItems) {
            OrderRequest.SaveDTO reqDTO = new OrderRequest.SaveDTO(Order.builder().build());
            reqDTO.setProductId(cartItem.getProduct().getId());
            reqDTO.setBuyQty(cartItem.getBuyQty());
            boolean saved = saveOrder(reqDTO, sessionUser);
        }
        // 장바구니 아이템 삭제
        cartRepository.deleteCart(cartItemIds);

        return true;
    }

    // 구매 취소하기
    @Transactional
    public void orderCancel(OrderRequest.CancelDTO reqDTO, Integer orderId){

        Order order = em.find(Order.class, orderId);
        if (order.getStatus().equals("취소완료")){
            return;
        }
        // 구매 취소
        orderRepository.orderCancel(reqDTO, orderId);
        // 구매 상태 업데이트
        orderRepository.updateOrderStatus(orderId, "취소완료");

    }

    // 구매하기
    public OrderRequest.SaveDTO findOrderById (Integer orderId, User sessionUser) {
        Order order = orderRepository.findOrderById(orderId);
        return new OrderRequest.SaveDTO(order);
    }

    // 구매하기
    @Transactional
    public boolean saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
        // 상품 재고 확인
//        Product product = productRepository.findById(reqDTO.getProductId());
//        if (product == null || product.getQty() < reqDTO.getBuyQty()) {
//            // 상품이 없거나 재고가 충분하지 않은 경우
//            return false;
//        }

        // 구매 완료 시 상태를 '구매완료"
         reqDTO.setStatus("구매완료");
        // 구매 내역 저장
        orderRepository.saveOrder(reqDTO, sessionUser.getId());
        // 상품 재고 업데이트
        orderRepository.updateQty(reqDTO.getBuyQty(), reqDTO.getProductId());

        return true;
    }

    public List<OrderResponse.ListDTO> getOrderListByUserId(Integer userId) {
        List<Order> orderList = orderRepository.getOrderListByUserId(userId);
        return orderList.stream().map(order ->
                new OrderResponse.ListDTO(order)).toList();
    }
}


