package com.example.buyer.cart;

import lombok.Data;

public class CartResponse {

    // 장바구니 담기
    @Data
    public static class SaveDTO{
        private Integer id;
        private Integer productId;
        private Integer buyQty;

        public SaveDTO(Cart cart) {
            this.id = cart.getId();
            this.productId = cart.getProduct().getId();
            this.buyQty = cart.getBuyQty();
        }
    }

}
