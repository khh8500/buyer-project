package com.example.buyer.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 목록보기
    public List<ProductResponse.ListDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product ->
                new ProductResponse.ListDTO(product)).toList();
    }
}
