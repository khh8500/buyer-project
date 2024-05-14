package com.example.buyer.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 선택된 상품 구매하기


    // 상품 상세보기
    public ProductResponse.DetailDTO findById(Integer id) {
        Product product = productRepository.findById(id);
        return new ProductResponse.DetailDTO(product);
    }

    // 상품 목록보기
    public List<ProductResponse.ListDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(product ->
                new ProductResponse.ListDTO(product)).toList();
    }
}
