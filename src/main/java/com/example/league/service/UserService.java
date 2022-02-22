package com.example.league.service;

import com.example.league.domain.Address;
import com.example.league.argumentresolver.SessionDto;

public interface UserService {

    Long signup(String email, String password,String role,String name, Address address);
    SessionDto login(String email, String password);
}
