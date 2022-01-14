package com.example.league.controller;

import com.example.league.dto.LoginDto;
import com.example.league.dto.SessionConst;
import com.example.league.dto.SessionDto;
import com.example.league.dto.UserDto;
import com.example.league.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto){
        userService.signup(userDto);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody LoginDto loginDto, HttpServletRequest request){
        SessionDto sessionDto = userService.login(loginDto);
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,sessionDto);
        return new ResponseEntity<>(sessionDto.getId(),HttpStatus.OK);
    }
}
