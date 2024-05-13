package com.example.buyer.cart;

import lombok.Data;

public class CartRequest {

    // 장바구니 구매하기
    @Data
    public static class UpdateDTO {
        private Integer cartId;
        private Integer buyQty;
        private Boolean status;
    }

    // 장바구니 담기
    @Data
    public static class SaveDTO {
        private Integer productId;
        private Integer buyQty;
    }

}
