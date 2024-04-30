package com.example.buyer.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        ProductResponse.DetailDTO product = productService.findById(id);
        request.setAttribute("product", product);

        return "product/detail";
    }

    @GetMapping("/product")
    public String list(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAll();
        request.setAttribute("productList", productList);

        return "product/list";
    }

    @GetMapping("/")
    public String main(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAll();
        request.setAttribute("productList", productList);
        return "/index";
    }

}
