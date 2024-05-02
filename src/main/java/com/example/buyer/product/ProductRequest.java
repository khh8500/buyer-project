package com.example.buyer.product;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class ProductRequest {

    @Data
    public static class ProductDTO {

        private Integer productId;
        private String pName;
        private Integer price;
        private Integer buyQty;
    }
}
