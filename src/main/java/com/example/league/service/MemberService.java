package com.example.league.service;


import com.example.league.dto.RequestTeamDto;

import java.util.List;

public interface MemberService {
    Long requestTeam(Long teamId, Long memberId);
    void WithdrawalTeamRequest(Long memberId, Long teamId);
    void leavingTeam(Long memberId);
    List<RequestTeamDto> findRequestList(Long memberId);
}
