package com.example.league.controller;

import com.example.league.argumentresolver.LoginUser;
import com.example.league.dto.RequestTeamDto;
import com.example.league.argumentresolver.SessionConst;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/request/{teamId}")
    public Long requestTeam(@PathVariable(name = "teamId")Long teamId,@LoginUser SessionDto sessionUser){
        Long okId = memberService.requestTeam(teamId,sessionUser.getId());
        return okId;
    }

    @GetMapping("/request")
    public List<RequestTeamDto> requestList(@LoginUser SessionDto sessionUser){
        return memberService.findRequestList(sessionUser.getId());
    }

    @DeleteMapping("/request/{teamId}")
    public String deleteRequest(@PathVariable(name = "teamId") Long teamId,@LoginUser SessionDto sessionUser){
        memberService.WithdrawalTeamRequest(sessionUser.getId(),teamId);
        return "ok";
    }

    @DeleteMapping("/request")
    public String leavingTeam(@LoginUser SessionDto sessionUser){
        memberService.leavingTeam(sessionUser.getId());
        return "ok";
    }
}
