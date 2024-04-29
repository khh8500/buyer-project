package com.example.buyer.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

    private final EntityManager em;

    // 상품 상세보기
    public Product findById(Integer id) {
        Query query = em.createNativeQuery("select * from product_tb where id=?", Product.class);
        query.setParameter(1, id);
        return (Product) query.getSingleResult();
    }

    // 상품 목록보기
    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb order by id desc", Product.class);
        return query.getResultList();
    }
}
