package com.example.buyer.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class LoginDTO {
        private String userId;
        private String password;
    }

    @Data
    public static class JoinDTO {
        private String userId;
        private String password;
        private String phone;
        private String address;

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .phone(phone)
                    .address(address)
                    .build();
        }
    }
}