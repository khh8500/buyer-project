package com.example.buyer.order;

import lombok.Data;

public class OrderRequest {

    // 구매 취소하기
    @Data
    public static class CancelDTO {
        private Integer productId;
        private Integer buyQty;
        private String status;
    }

    // 주문하기(구매하기)
    @Data
    public static class SaveDTO {
        private Integer productId;
//        private String name; // 안받아도 됐음
//        private Integer price; // 안받아도 됐음
        private Integer buyQty;
        private String status;

        public SaveDTO(Order order) {
            this.productId = order.getProduct().getId();;
            this.buyQty = order.getBuyQty();
            this.status = order.getStatus();
        }
    }

}

// 주문하기(구매하기)
//    @Data
//    public static class SaveDTO {
//        // user
//        private User user;
//
//        // product
//        private Product product;
//
//        // order
//        private Integer buyQty;
//
//        public Order toEntity() {
//            return Order.builder()
//                    .user(user)
//                    .product(product)
//                    .buyQty(buyQty)
//                    .build();
//        }
//    }
