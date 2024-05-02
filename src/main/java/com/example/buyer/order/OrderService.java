package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.product.ProductRequest;
import com.example.buyer.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    // 주문하기(구매하기)
    @Transactional
    public void save(OrderRequest.SaveDTO reqDTO) {
        // 주문 수량이 0 초과인 경우에만 주문 처리
        if (reqDTO.getBuyQty() > 0) {
            orderRepository.save(reqDTO);
        }
        // 유저 데이터 가져오기

        // 상품의 재고 업데이트
        orderRepository.updateQty(reqDTO.getBuyQty(), reqDTO.getProduct().getId());

    }

    public User findUserById(Integer userId) {
        User user = orderRepository.findUserById(userId);

        return user;
    }

    // 주문 목록보기
    @Transactional
    public List<Order> getOrderListByUserId (Integer userId) {
        List<Order> orderList = orderRepository.getOrderListByUserId(userId);

        return orderList;
    }

    // 주문 상품 정보가져오기
//    @Transactional
//    public Product getOrderProduct (ProductRequest.ProductDTO reqDTO) {
//
//    }

}
      // 주문하기(구매하기)
//    @Transactional
//    public void order(OrderRequest.OrderDTO reqDTO) {
//        orderRepository.order(reqDTO);
//    }

//    public OrderResponse.OrderDTO findUserById(Integer userId) {
//        User user = orderRepository.findUserById(userId);
//
//        OrderResponse.OrderDTO orderDTO = new OrderResponse.OrderDTO();
//
//        orderDTO.setUserId(user.getId());
//        orderDTO.setPhone(user.getPhone());
//        orderDTO.setAddress(user.getAddress());
//
//        return orderDTO;
//    }

