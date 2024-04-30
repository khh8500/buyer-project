package com.example.buyer.order;

import lombok.Data;

public class OrderResponse {

    @Data
    public static class OrderDTO {
        // 유저
        private Integer userId;
        private String phone;
        private String address;

        // 주문 상품
        private Integer productId;
        private String productName;

        // 주문 상품 수량
        private Integer qty;
    }

}
