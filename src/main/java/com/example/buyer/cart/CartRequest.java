package com.example.buyer.cart;

import com.example.buyer.product.Product;
import lombok.Builder;
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
        private Product product;
        private Integer buyQty;

        @Builder
        public SaveDTO(Product product, Integer buyQty) {
            this.product = product;
            this.buyQty = buyQty;
        }
    }
}
