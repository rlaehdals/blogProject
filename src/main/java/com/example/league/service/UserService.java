package com.example.league.service;

import com.example.league.dto.LoginDto;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.dto.UserDto;

public interface UserService {

    Long signup(UserDto userDto);
    SessionDto login(LoginDto loginDto);
}
