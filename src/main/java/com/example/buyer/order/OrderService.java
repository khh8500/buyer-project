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

    @Transactional
    public void saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
        orderRepository.saveOrder(reqDTO, sessionUser.getId());
    }

    public List<OrderResponse.ListDTO> getOrderListByUserId(Integer userId) {
        List<Order> orderList = orderRepository.getOrderListByUserId(userId);
        return orderList.stream().map(order ->
                new OrderResponse.ListDTO(order)).toList();
    }
}


