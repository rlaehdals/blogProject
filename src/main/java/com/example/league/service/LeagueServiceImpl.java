package com.example.league.service;


import com.example.league.domain.League;
import com.example.league.domain.ROLE;
import com.example.league.domain.Team;
import com.example.league.domain.User;
import com.example.league.domain.request.RequestLeague;
import com.example.league.dto.LeagueDto;
import com.example.league.dto.RequestLeagueDto;
import com.example.league.dto.RestLeagueDto;
import com.example.league.exception.*;
import com.example.league.repository.LeagueRepository;
import com.example.league.repository.RequestLeagueRepository;
import com.example.league.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LeagueServiceImpl implements LeagueService{

    private final LeagueRepository leagueRepository;
    private final RequestLeagueRepository requestLeagueRepository;
    private final UserRepository userRepository;
    @Override
    public Long createLeague(LeagueDto leagueDto, Long userId) {
        User user = userRepository.findById(userId).get();
        checkDuplicateTeamName(leagueDto);
        League league = League.createLeague(leagueDto.getSize(), leagueDto.getName(),user);
        return leagueRepository.save(league).getId();
    }

    @Override
    public void acceptLeague(Long requestLeagueId) {
        RequestLeague requestLeague = requestLeagueRepository.findWithFetchJoinUserById(requestLeagueId).get();
        Team team = requestLeague.getTeam();

        if(requestLeague.getRequest()){
            throw new AlreadyAcceptTeamException("이미 승인이 완료된 신청입니다.");
        }

        if(team.getLeague()!=null){
            throw new AlreadyLeagueException("이미 리그에 참가하고 있습니다.");
        }

        requestLeague.acceptRequest();
        requestLeague.increaseLeagueSize();
    }

    @Override
    public List<RequestLeagueDto> findRequestList(Long leagueId) {
        return requestLeagueRepository.findByLeagueId(leagueId)
                .stream().map(a -> new RequestLeagueDto(a.getRequest(),a.getTeam().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RestLeagueDto> findRestLeagueSeat() {
        return leagueRepository.findByRestSeat().stream().map(a -> new RestLeagueDto(a.getLeagueName(),
                a.getScaleSize(),a.getNowSize())).collect(Collectors.toList());
    }

    @Override
    public RestLeagueDto findLeagueByName(String leagueName) {
        League league = leagueRepository.findByName(leagueName).orElseThrow(
                () -> {
                    throw new NotExistLeagueNameException("해당 이름의 리그는 없습니다.");
                }
        );

        return new RestLeagueDto(league.getLeagueName(),league.getScaleSize(),league.getNowSize());

    }
    private void checkDuplicateTeamName(LeagueDto leagueDto) {
        leagueRepository.findByName(leagueDto.getName()).ifPresent(
                a -> {
                    throw new DuplicateLeagueNameException("이미 같은 이름의 리그가 있습니다.");
                }
        );
    }
}
