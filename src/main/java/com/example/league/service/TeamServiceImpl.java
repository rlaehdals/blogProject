package com.example.league.service;

import com.example.league.domain.League;
import com.example.league.domain.Team;
import com.example.league.domain.User;
import com.example.league.domain.request.RequestLeague;
import com.example.league.domain.request.RequestTeam;
import com.example.league.dto.RequestTeamDto;
import com.example.league.dto.TeamDto;
import com.example.league.exception.*;
import com.example.league.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final RequestTeamRepository requestTeamRepository;
    private final RequestLeagueRepository requestLeagueRepository;
    private final LeagueRepository leagueRepository;

    @Override
    public Long createTeam(String name, int size, Long userId) {
        User user = userRepository.findById(userId).get();
        checkDuplicateTeamName(name);
        Team team = Team.createTeam(user, name, size);
        return teamRepository.save(team).getId();
    }

    @Override
    public List<RequestTeamDto> findRequestList(Long teamId) { //팀에 신청한 request 목록
        return requestTeamRepository.findByTeamId(teamId, Sort.by(Sort.Direction.DESC
                , "createdTime")).stream().map(a -> new RequestTeamDto(a.getRequest(),a.getUser().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void acceptMember(Long requestTeamId) {
        RequestTeam requestTeam = requestTeamRepository.findWithFetchJoinUserById(requestTeamId).orElseThrow(
                () -> {
                    throw new NotExistRequestException("존재하지 않는 요청입니다.");
                }
        );
        User user = requestTeam.getUser();
        if (user.getTeam()!=null){
            throw new AlreadyTeamException("이미 팀에 속해있습니다.");
        }
        if(requestTeam.getRequest()){
            throw new AlreadyAcceptMemberException("이미 승인된 요청입니다.");
        }
        requestTeam.acceptRequest();
    }

    @Override
    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        team.removeTeam();
        teamRepository.deleteById(teamId);
    }

    @Override
    public Long updateTeam(String name, int size, Long teamId, Long userId) {
        User user = userRepository.findById(userId).get();
        Team team = teamRepository.findById(teamId).get();
        checkDuplicateTeamName(name);
        team.update(name,size);
        return team.getId();
    }

    @Override
    public List<TeamDto.RestTeamDto> findRestTeamSeat() {
        return teamRepository.findByRestSeat().stream().map( a -> new TeamDto.RestTeamDto(a.getName(),a.getMaxSize(),a.getNowSize()))
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto.RestTeamDto findTeamByName(String teamName) {
        Team team = teamRepository.findByName(teamName).orElseThrow(
                () -> {
                    throw new NotMatchingTeamNameException("일치하는 이름의 팀이 없습니다.");
                }
        );

        return new TeamDto.RestTeamDto(team.getName(),team.getMaxSize(),team.getNowSize());
    }

    @Override
    public Long requestLeague(Long leagueId, Long teamId) {
        League league = leagueRepository.findById(leagueId).get();
        Team team = teamRepository.findById(teamId).get();

        RequestLeague requestLeague = RequestLeague.createRequestLeague(team, league);

        if(team.getLeague()!=null){
            throw new AlreadyTeamException("이미 리그에 참가하고 있습니다.");
        }
        requestLeagueRepository.findByTeamId(teamId).ifPresent(
                a -> {
                    throw new DuplicateRequestToLeagueException("이미 리그에 신청한 기록이 있습니다.");
                }
        );

        return requestLeagueRepository.save(requestLeague).getId();
    }

    @Override
    public void withdrawalLeagueRequest(Long teamId, Long leagueId) {
        RequestLeague requestLeague = requestLeagueRepository.findByMemberIdAndTeamId(leagueId, teamId).get();
        requestLeague.removeRequest();
        requestLeagueRepository.deleteById(requestLeague.getId());
    }

    private void checkDuplicateTeamName(String name) {
        teamRepository.findByName(name).ifPresent(
                a -> {
                    throw new DuplicateTeamNameException("이미 같은 이름의 팀이 있습니다.");
                }
        );
    }
}
