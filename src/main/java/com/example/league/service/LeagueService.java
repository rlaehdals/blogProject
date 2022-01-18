package com.example.league.service;

import com.example.league.dto.*;

import java.util.List;

public interface LeagueService {
    Long createLeague(LeagueDto leagueDto, Long userId);
    void acceptLeague(Long requestLeagueId);
    List<RequestLeagueDto> findRequestList(Long leagueId);
    List<RestLeagueDto> findRestLeagueSeat();
    RestLeagueDto findLeagueByName(String leagueName);
}
