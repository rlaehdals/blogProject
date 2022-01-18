package com.example.league.service;

import com.example.league.domain.*;
import com.example.league.domain.request.RequestLeague;
import com.example.league.dto.*;
import com.example.league.exception.AlreadyAcceptTeamException;
import com.example.league.exception.DuplicateLeagueNameException;
import com.example.league.repository.LeagueRepository;
import com.example.league.repository.RequestLeagueRepository;
import com.example.league.repository.TeamRepository;
import com.example.league.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LeagueServiceImplTest {


    @Autowired
    LeagueRepository leagueRepository;

    @Autowired
    LeagueServiceImpl leagueService;

    @Autowired
    RequestLeagueRepository requestLeagueRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;



    @Test
    void createLeagueSuccess(){
        Address address = getAddress();
        User host = getHost(address);
        Long hostId = userService.signup(new UserDto(host.getEmail(), host.getPassword(), host.getName()
                , "user", address.getCity(), address.getStreet()));
        Long leagueId = leagueService.createLeague(new LeagueDto("전국대회", 10), hostId);
        League league = leagueRepository.findById(leagueId).get();

        assertThat(league.getLeagueName()).isEqualTo("전국대회");
        assertThat(league.getEmail()).isEqualTo(host.getEmail());
        assertThat(league.getScaleSize()).isEqualTo(10);
    }

    @Test
    void createLeagueFailDuplicateName(){
        Address address = getAddress();
        User host = getHost(address);
        Long hostId = userService.signup(new UserDto(host.getEmail(), host.getPassword(), host.getName()
                , "user", address.getCity(), address.getStreet()));
        Long leagueId = leagueService.createLeague(new LeagueDto("전국대회", 10), hostId);
        League league = leagueRepository.findById(leagueId).get();

        assertThatThrownBy(() -> leagueService.createLeague(new LeagueDto("전국대회",10),hostId))
                .isInstanceOf(DuplicateLeagueNameException.class);
    }
    @Test
    void acceptRequestSuccess(){
        Address address = getAddress();
        User founder = getFounder(address);
        User host = getHost(address);
        Long founderId = userService.signup(new UserDto(founder.getEmail(), founder.getPassword(), founder.getName()
                , "user", address.getCity(), address.getStreet()));
        Long hostId = userService.signup(new UserDto(host.getEmail(), host.getPassword(), host.getName()
                , "user", address.getCity(), address.getStreet()));
        Long team1Id = teamService.createTeam(new TeamDto("상명", 10), founderId);
        Long leagueId = leagueService.createLeague(new LeagueDto("전국대회", 10), hostId);
        Long requestId = teamService.requestLeague(leagueId, team1Id);
        leagueService.acceptLeague(requestId);

        RequestLeague requestLeague = requestLeagueRepository.findById(requestId).get();

        Team team = requestLeague.getTeam();
        League league = requestLeague.getLeague();

        assertThat(team.getEmail()).isEqualTo(founder.getEmail());
        assertThat(league.getEmail()).isEqualTo(host.getEmail());
        assertThat(league.getNowSize()).isEqualTo(1);
    }

    @Test
    void acceptRequestFailAlreadyJoinLeague() {
        Address address = getAddress();
        User founder = getFounder(address);
        User host = getHost(address);
        Long founderId = userService.signup(new UserDto(founder.getEmail(), founder.getPassword(), founder.getName()
                , "user", address.getCity(), address.getStreet()));
        Long hostId = userService.signup(new UserDto(host.getEmail(), host.getPassword(), host.getName()
                , "user", address.getCity(), address.getStreet()));
        Long team1Id = teamService.createTeam(new TeamDto("상명", 10), founderId);
        Long leagueId = leagueService.createLeague(new LeagueDto("전국대회", 10), hostId);
        Long requestId = teamService.requestLeague(leagueId, team1Id);
        leagueService.acceptLeague(requestId);

        RequestLeague requestLeague = requestLeagueRepository.findById(requestId).get();

        assertThatThrownBy(() -> leagueService.acceptLeague(requestId))
                .isInstanceOf(AlreadyAcceptTeamException.class);
    }

    @Test
    void findRequestList(){
        Address address = getAddress();
        User founder = getFounder(address);
        User host = getHost(address);
        Long founderId = userService.signup(new UserDto(founder.getEmail(), founder.getPassword(), founder.getName()
                , "user", address.getCity(), address.getStreet()));
        Long hostId = userService.signup(new UserDto(host.getEmail(), host.getPassword(), host.getName()
                , "user", address.getCity(), address.getStreet()));
        Long team1Id = teamService.createTeam(new TeamDto("상명", 10), founderId);
        Long leagueId = leagueService.createLeague(new LeagueDto("전국대회", 10), hostId);
        Long requestId = teamService.requestLeague(leagueId, team1Id);
        List<RequestLeagueDto> requestList = leagueService.findRequestList(leagueId);
        assertThat(requestList.size()).isEqualTo(1);
        assertThat(requestList.get(0).getTeamName()).isEqualTo("상명");
    }

    @Test
    void findRestLeagueList(){
        Address address = getAddress();
        User founder = getFounder(address);
        User host = getHost(address);
        Long founderId = userService.signup(new UserDto(founder.getEmail(), founder.getPassword(), founder.getName()
                , "user", address.getCity(), address.getStreet()));
        Long hostId = userService.signup(new UserDto(host.getEmail(), host.getPassword(), host.getName()
                , "user", address.getCity(), address.getStreet()));
        Long team1Id = teamService.createTeam(new TeamDto("상명", 10), founderId);
        Long leagueId1 = leagueService.createLeague(new LeagueDto("전국대회1", 10), hostId);
        Long leagueId2= leagueService.createLeague(new LeagueDto("전국대회2", 1), hostId);

        Long requestId = teamService.requestLeague(leagueId2, team1Id);

        leagueService.acceptLeague(requestId);

        List<RestLeagueDto> restLeagueSeat = leagueService.findRestLeagueSeat();

        assertThat(restLeagueSeat.size()).isEqualTo(1);
        assertThat(restLeagueSeat.get(0).getLeagueName()).isEqualTo("전국대회1");
    }





        private Address getAddress() {
        return Address.builder()
                .street("s")
                .city("a")
                .build();
    }

    private User getHost(Address address) {
        return User.createUser("rkdlem48", "asdf", "kim", address, ROLE.LEAGUE_HOST);
    }
    private User getFounder(Address address){
        return User.createUser("rkdlem2", "asdf", "kim", address, ROLE.TEAM_FOUNDER);

    }
}