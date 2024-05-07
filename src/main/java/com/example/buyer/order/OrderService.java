package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRepository;
import com.example.buyer.product.ProductRequest;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import com.example.buyer.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 구매하기
    @Transactional
    public boolean saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
        // 상품 재고 확인
        Product product = productRepository.findById(reqDTO.getProductId());
        if (product == null || product.getQty() < reqDTO.getBuyQty()) {
            // 상품이 없거나 재고가 충분하지 않은 경우
            return false;
        }

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


