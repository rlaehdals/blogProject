package com.example.league.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupDto{
        public String email;
        public String password;
        public String name;
        public String role;
        public String city;
        public String street;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginDto{
        public String email;
        public String password;
    }
}
