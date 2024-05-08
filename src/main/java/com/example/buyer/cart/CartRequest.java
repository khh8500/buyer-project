package com.example.buyer.cart;

import lombok.Data;

public class CartRequest {

    // 장바구니 담기
    @Data
    public static class SaveDTO {
        private Integer productId;
        private Integer buyQty;

    }
}
