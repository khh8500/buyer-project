package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import lombok.Data;

public class OrderRequest {

    // 주문하기(구매하기)
    @Data
    public static class SaveDTO {
        // user
        private User user;

        // product
        private Product product;

        // order
        private Integer buyQty;

        public Order toEntity() {
            return Order.builder()
                    .user(this.user)
                    .product(this.product)
                    .buyQty(this.buyQty)
                    .build();
        }
    }

}

    // 주문하기(구매하기)
//    @Data
//    public static class OrderDTO {
//        //user
//        private Integer userId;
//        private String name;
//        private String phone;
//        private String address;
//
//        // product
//        private Integer productId;
//        private String pName;
//        private Integer price;
//        private Integer qty;
//
//        // order
//        private Integer buyQty;
//
//        }

