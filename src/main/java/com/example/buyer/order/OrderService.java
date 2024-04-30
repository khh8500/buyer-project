package com.example.buyer.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문하기(구매하기)
    @Transactional
    public void order(OrderRequest.OrderDTO reqDTO) {
        orderRepository.order(reqDTO);
    }

    public void decreaseQty (){

    }
}
