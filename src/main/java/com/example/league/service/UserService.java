package com.example.league.service;

import com.example.league.dto.LoginDto;
import com.example.league.dto.SessionDto;
import com.example.league.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {

    Long signup(UserDto userDto);
    SessionDto login(LoginDto loginDto);
}
