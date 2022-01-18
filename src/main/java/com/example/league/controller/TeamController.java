package com.example.league.controller;

import com.example.league.argumentresolver.LoginUser;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.dto.RequestTeamDto;
import com.example.league.dto.RestTeamDto;
import com.example.league.dto.TeamDto;
import com.example.league.dto.TeamRequestToLeagueDto;
import com.example.league.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/")
    public RestTeamDto findByName(@RequestParam(name =" teamName") String teamName){
        return teamService.findTeamByName(teamName);
    }

    @GetMapping("/rest")
    public List<RestTeamDto> findRestTeam(){
        return teamService.findRestTeamSeat();
    }

    @PostMapping("/new")
    public Long createTeam(@RequestBody TeamDto teamDto, @LoginUser SessionDto sessionDto){
        return teamService.createTeam(teamDto, sessionDto.getId());
    }

    @DeleteMapping("/{teamId}")
    public String deleteTeam(@PathVariable(name = "teamId") Long teamId){
        teamService.deleteTeam(teamId);
        return "ok";
    }

    @PatchMapping("/{teamId}")
    public Long updateTeam(@RequestBody TeamDto teamDto, @PathVariable(name = "teamId") Long teamId
            , @LoginUser SessionDto sessionDto){
        return teamService.updateTeam(teamDto,teamId, sessionDto.getId());
    }

    @PostMapping("/request/{requestId}")
    public String acceptMember(@PathVariable(name = "requestId") Long requestId){
        teamService.acceptMember(requestId);
        return "ok";
    }

    @GetMapping("/request/{teamId}")
    public List<RequestTeamDto> requestList(@PathVariable(name = "teamId") Long teamId){
        return teamService.findRequestList(teamId);
    }

    @PostMapping("/league/{leagueId}")
    public Long requestLeague(@PathVariable(name= "leagueId") Long leagueId,
                              @RequestParam(name = "teamId") Long teamId){
        return teamService.requestLeague(teamId,teamId);
    }

    @DeleteMapping("/league/{leagueId}")
    public String withdrawLeague(@PathVariable(name = "leagueId")Long leagueId, @RequestParam(name = "teamId") Long teamId){
        teamService.withdrawalLeagueRequest(teamId,leagueId);
        return "ok";
    }
}
