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
    public ErrorDto noLoginMember(){
        return new ErrorDto("로그인을 해주세요.");
    }

    @GetMapping("/member/notAuthority")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noAuthorityMember(){
        return new ErrorDto("해당 유저는 멤버 권한이 없습니다.");
    }

    @GetMapping("/team/noTeam")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noLoginTeam(){
        return new ErrorDto("로그인을 해주세요.");
    }

    @GetMapping("/team/notAuthority")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noAuthorityTeam(){
        return new ErrorDto("해당 유저는 팀 권한이 없습니다.");
    }

    @GetMapping("/team/notExistRequest")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto notExistRequest(){
        return new ErrorDto("존재하지 않는 가입 신청건 입니다.");
    }

    @GetMapping("/league/noLeague")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noLoginLeague(){
        return new ErrorDto("로그인을 해주세요.");
    }

    @GetMapping("/league/notAuthority")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto noAuthorityLeague(){
        return new ErrorDto("해당 유저는 리그 권한이 없습니다.");
    }
}
