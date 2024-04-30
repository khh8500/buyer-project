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
        Query query = em.createQuery("select p from Product p where id=:id", Product.class);
        query.setParameter("id", id);
        return (Product) query.getSingleResult();
    }

    // 상품 목록보기
    public List<Product> findAll() {
        Query query = em.createQuery("select p from Product p order by id desc", Product.class);
        return query.getResultList();
    }
}
