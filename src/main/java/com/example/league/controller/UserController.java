package com.example.league.controller;

import com.example.league.aop.MethodLog;
import com.example.league.argumentresolver.SessionConst;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.domain.Address;
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

    @MethodLog
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto.SignupDto signupDto){
        userService.signup(signupDto.getEmail(),signupDto.getPassword(),signupDto.getRole(),
                signupDto.getName(),new Address(signupDto.getCity(),signupDto.getStreet()));
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    @MethodLog
    @PostMapping("/login")
    public ResponseEntity<Long> login(@RequestBody UserDto.LoginDto loginDto, HttpServletRequest request){
        SessionDto sessionDto = userService.login(loginDto.getEmail(),loginDto.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,sessionDto);
        return new ResponseEntity<>(sessionDto.getId(),HttpStatus.OK);
    }
}
