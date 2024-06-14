package com.example.buyer.order;

import com.example.buyer.product.Product;
import lombok.Data;

public class OrderResponse {

    @Data
    public static class ListDTO {
        private Integer id;
        private String name;
        private Integer price;
        private Integer buyQty;
        private String pic;
        private String status;

        public ListDTO(Order order) {
            this.id = order.getId();
            this.name = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.buyQty = order.getBuyQty();
            this.pic = order.getProduct().getPic();
            this.status = order.getStatus();
        }
    }

}

//    @Data
//    public static class SaveDTO {
//        // 유저
//        private Integer userId;
//        private String name;
//        private String phone;
//        private String address;
//
//        // 주문 상품
//        private Integer productId;
//        private String pName;
//        private Integer price;
//
//        // 주문 수량
//        private Integer buyQty;
//
//        public SaveDTO(Order order) {
//            this.userId = order.getUser().getId();
//            this.name = order.getUser().getName();
//            this.address = order.getUser().getAddress();
//            this.phone = order.getUser().getPhone();
//            this.productId = order.getProduct().getId();
//            this.pName = order.getProduct().getName();
//            this.price = order.getProduct().getPrice();
//            this.buyQty = order.getBuyQty();
//        }
//    }
