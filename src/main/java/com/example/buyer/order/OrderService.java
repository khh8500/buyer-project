package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRepository;
import com.example.buyer.product.ProductRequest;
import com.example.buyer.product.ProductResponse;
import com.example.buyer.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void save (OrderRequest.SaveDTO reqDTO) {
        orderRepository.save(reqDTO);
    }

    public List<OrderResponse.ListDTO> getOrderListByUserId(Integer userId) {
        List<Order> orderList = orderRepository.getOrderListByUserId(userId);
        return orderList.stream().map(order ->
                new OrderResponse.ListDTO(order)).toList();
    }
}


