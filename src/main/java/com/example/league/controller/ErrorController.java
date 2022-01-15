package com.example.league.controller;

import com.example.league.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/err")
public class ErrorController {


    @GetMapping("/member/noUser")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noLogin(){
        return new ErrorDto("로그인을 해주세요.");
    }

    @GetMapping("/member/notAuthority")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noAuthority(){
        return new ErrorDto("해당 유저는 멤버 권한이 없습니다.");
    }
}
