package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
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

        if (reqDTO.getBuyQty() > 0) {
            orderRepository.updateQty(reqDTO);
        }
    }
//    @Transactional
//    public void order(OrderRequest.OrderDTO reqDTO) {
//        orderRepository.order(reqDTO);
//    }

//    public User findUserById(OrderRequest.OrderDTO reqDTO) {
//        User user = reqDTO.getUser();
//        return orderRepository.findUserById(user.getId());
//    }

}
