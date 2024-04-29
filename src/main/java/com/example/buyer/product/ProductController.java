package com.example.buyer.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public String list (HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAll();
        request.setAttribute("productList", productList);

        return "/product/list";
    }
}
