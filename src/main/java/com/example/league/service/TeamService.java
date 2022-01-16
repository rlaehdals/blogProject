package com.example.league.service;


import com.example.league.dto.RequestTeamDto;
import com.example.league.dto.RestTeamDto;
import com.example.league.dto.TeamDto;
import com.example.league.dto.TeamRequestToLeagueDto;

import java.util.List;

public interface TeamService {
    Long createTeam(TeamDto teamDto, Long userId);
    List<RequestTeamDto> findRequestList(Long teamId);
    void acceptMember(Long requestTeamId);
    void deleteTeam(Long teamId);
    Long updateTeam(TeamDto teamDto, Long teamId, Long userId);
    List<RestTeamDto> findRestTeamSeat();
    RestTeamDto findTeamByName(String teamName);
    Long requestLeague(Long leagueId, Long teamId);
    void withdrawalLeagueRequest(Long teamId, Long leagueId);
}
