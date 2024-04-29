package com.example.buyer.user;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;

    // 회원가입하기
    @Transactional
    public User save (User user){
        em.persist(user);
        return user;
    }
}
