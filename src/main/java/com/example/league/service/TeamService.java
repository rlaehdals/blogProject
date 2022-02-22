package com.example.league.service;


import com.example.league.dto.RequestTeamDto;
import com.example.league.dto.TeamDto;

import java.util.List;

public interface TeamService {
    Long createTeam(String name, int size, Long userId);
    List<RequestTeamDto> findRequestList(Long teamId);
    void acceptMember(Long requestTeamId);
    void deleteTeam(Long teamId);
    Long updateTeam(String name, int size, Long teamId, Long userId);
    List<TeamDto.RestTeamDto> findRestTeamSeat();
    TeamDto.RestTeamDto findTeamByName(String teamName);
    Long requestLeague(Long leagueId, Long teamId);
    void withdrawalLeagueRequest(Long teamId, Long leagueId);
}
