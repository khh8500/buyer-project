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
        Query query = em.createQuery(
                "select u from User u where u.userId=:userId and u.password=:password", User.class);
        query.setParameter("userId", reqDTO.getUserId());
        query.setParameter("password", reqDTO.getPassword());

        User user = (User) query.getSingleResult();
        return user;
    }
}
