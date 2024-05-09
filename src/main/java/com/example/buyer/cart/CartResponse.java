package com.example.buyer.cart;

import lombok.Data;

public class CartResponse {

    @Data
    public static class SaveDTO{
        private Integer productId;
        private Integer buyQty;

        public SaveDTO(Cart cart) {
            this.productId = cart.getProduct().getId();
            this.buyQty = cart.getBuyQty();
        }
    }

}
