package com.example.buyer.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String userId;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .email(email)
                    .build();
        }
    }
}