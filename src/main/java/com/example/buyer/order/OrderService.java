package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRepository;
import com.example.buyer.product.ProductRequest;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import com.example.buyer.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final EntityManager em;

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
    @Transactional
    public boolean saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
        // 상품 재고 확인
        Product product = productRepository.findById(reqDTO.getProductId());
        if (product == null || product.getQty() < reqDTO.getBuyQty()) {
            // 상품이 없거나 재고가 충분하지 않은 경우
            return false;
        }

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


