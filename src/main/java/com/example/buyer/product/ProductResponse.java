package com.example.buyer.product;

import lombok.Data;

public class ProductResponse {

    @Data
    public static class DetailDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;
        private String pic;

        public DetailDTO(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
            this.qty = product.getQty();
            this.id = product.getId();
            this.pic = product.getPic();

        }
    }

    @Data
    public static class ListDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;
        private String pic;


        public ListDTO(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
            this.qty = product.getQty();
            this.id = product.getId();
            this.pic = product.getPic();

        }
    }
}
