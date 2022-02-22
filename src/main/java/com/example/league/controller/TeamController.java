package com.example.league.controller;

import com.example.league.aop.MethodLog;
import com.example.league.argumentresolver.LoginUser;
import com.example.league.argumentresolver.SessionDto;
import com.example.league.dto.RequestTeamDto;
import com.example.league.dto.TeamDto;
import com.example.league.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;
    @MethodLog
    @GetMapping("/")
    public TeamDto.RestTeamDto findByName(@RequestParam(name =" teamName") String teamName){
        return teamService.findTeamByName(teamName);
    }
    @MethodLog
    @PostMapping("/")
    public Long createTeam(@RequestBody TeamDto.CreateOrUpdateTeamDto createOrUpdateTeamDto, @LoginUser SessionDto sessionDto){
        return teamService.createTeam(createOrUpdateTeamDto.getName(),createOrUpdateTeamDto.getSize()
                , sessionDto.getId());
    }

    @MethodLog
    @GetMapping("/rest")
    public List<TeamDto.RestTeamDto> findRestTeam(){
        return teamService.findRestTeamSeat();
    }

    @MethodLog
    @DeleteMapping("/{teamId}")
    public String deleteTeam(@PathVariable(name = "teamId") Long teamId){
        teamService.deleteTeam(teamId);
        return "ok";
    }

    @MethodLog
    @PatchMapping("/{teamId}")
    public Long updateTeam(@RequestBody TeamDto.CreateOrUpdateTeamDto createOrUpdateTeamDto, @PathVariable(name = "teamId") Long teamId
            , @LoginUser SessionDto sessionDto){
        return teamService.updateTeam(createOrUpdateTeamDto.getName(),createOrUpdateTeamDto.getSize(),teamId, sessionDto.getId());
    }

    @MethodLog
    @PostMapping("/request/{requestId}")
    public String acceptMember(@PathVariable(name = "requestId") Long requestId){
        teamService.acceptMember(requestId);
        return "ok";
    }

    @MethodLog
    @GetMapping("/request/{teamId}")
    public List<RequestTeamDto> requestList(@PathVariable(name = "teamId") Long teamId){
        return teamService.findRequestList(teamId);
    }

    @MethodLog
    @PostMapping("/league/{leagueId}")
    public Long requestLeague(@PathVariable(name= "leagueId") Long leagueId,
                              @RequestParam(name = "teamId") Long teamId){
        return teamService.requestLeague(teamId,teamId);
    }

    @MethodLog
    @DeleteMapping("/league/{leagueId}")
    public String withdrawLeague(@PathVariable(name = "leagueId")Long leagueId, @RequestParam(name = "teamId") Long teamId){
        teamService.withdrawalLeagueRequest(teamId,leagueId);
        return "ok";
    }
}
