package com.example.buyer.order;

import com.example.buyer.product.Product;
import com.example.buyer.user.User;
import lombok.Data;

public class OrderRequest {

    // 주문하기(구매하기)
    @Data
    public static class OrderDTO {
        // user
//        private Integer userId;
//        private String name;
//        private String phone;
//        private String address;
        private User user;

        // product
//        private Integer productId;
//        private String pName;
//        private Integer price;
//        private Integer qty;
        private Product product;
        // order
        private Integer buyQty;

        public Order toEntity(Product product, User user){
            return Order.builder()
                    .user(user)
                    .product(product)
                    .buyQty(buyQty)
                    .build();
        }
    }
}
