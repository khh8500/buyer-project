package com.example.buyer.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    // 로그인하기
    public User findByUserIdAndPassword(UserRequest.LoginDTO reqDTO) {
        Query query = em.createNativeQuery("select * from user_tb where user_id=? and password=?", User.class);
        query.setParameter(1, reqDTO.getUserId());
        query.setParameter(2, reqDTO.getPassword());

        User user = (User) query.getSingleResult();
        return user;
    }
}
