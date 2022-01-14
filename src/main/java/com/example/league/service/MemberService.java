package com.example.league.service;


import com.example.league.dto.MemberRequestToTeamDto;
import com.example.league.dto.RequestTeamDto;

import java.util.List;

public interface MemberService {
    Long requestTeam(MemberRequestToTeamDto memberRequestToTeamDto, Long memberId);
    void WithdrawalTeamRequest(Long memberId, Long teamId);
    void leavingTeam(Long memberId);
    List<RequestTeamDto> findRequestList(Long memberId);
}
