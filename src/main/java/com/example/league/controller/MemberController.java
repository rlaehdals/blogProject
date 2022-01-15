package com.example.league.controller;

import com.example.league.dto.RequestTeamDto;
import com.example.league.argumentresolver.SessionConst;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/request/{teamId}")
    public Long requestTeam(@PathVariable(name = "teamId")Long teamId,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long okId = memberService.requestTeam(teamId,sessionUser.getId());
        return okId;
    }

    @GetMapping("/request")
    public List<RequestTeamDto> requestList(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return memberService.findRequestList(sessionUser.getId());
    }

    @DeleteMapping("/request/{teamId}")
    public String deleteRequest(@PathVariable(name = "teamId") Long teamId,HttpServletRequest request){
        HttpSession session = request.getSession(false);
        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberService.WithdrawalTeamRequest(sessionUser.getId(),teamId);
        return "ok";
    }

    @DeleteMapping("/request")
    public String leavingTeam(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        SessionDto sessionUser = (SessionDto) session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberService.leavingTeam(sessionUser.getId());
        return "ok";
    }
}
