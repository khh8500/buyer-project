package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import lombok.Data;

public class OrderRequest {

    // 주문하기(구매하기)
    @Data
    public static class SaveDTO {
        private Integer productId;
        private String name; // 안받아도 됐음
        private Integer price; // 안받아도 됐음
        private Integer buyQty;
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




