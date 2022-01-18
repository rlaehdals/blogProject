package com.example.league.controller;

import com.example.league.argumentresolver.LoginUser;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.dto.LeagueDto;
import com.example.league.dto.RequestLeagueDto;
import com.example.league.dto.RestLeagueDto;
import com.example.league.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/league")
public class LeagueController {
    private final LeagueService leagueService;


    @GetMapping("/")
    public RestLeagueDto findByName(@RequestParam(name = "leagueName") String leagueName){
        return leagueService.findLeagueByName(leagueName);
    }

    @GetMapping("/rest")
    public List<RestLeagueDto> findRestLeagueDto(){
        return leagueService.findRestLeagueSeat();
    }

    @PostMapping("/")
    public Long createLeague(@RequestBody LeagueDto leagueDto, @LoginUser SessionDto hostUser){
        return leagueService.createLeague(leagueDto, hostUser.getId());
    }

    @GetMapping("/request/{leagueId}")
    public List<RequestLeagueDto> findRequestList(@PathVariable(name = "leagueId") Long leagueId){
        return leagueService.findRequestList(leagueId);
    }

    @PostMapping("/request/{requestLeagueId}")
    public String acceptLeague(@PathVariable(name = "requestLeagueId")Long requestLeagueId){
        leagueService.acceptLeague(requestLeagueId);
        return "ok";
    }
}
