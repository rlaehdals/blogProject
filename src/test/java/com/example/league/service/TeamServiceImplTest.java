package com.example.league.service;

import com.example.league.domain.Address;
import com.example.league.domain.ROLE;
import com.example.league.domain.Team;
import com.example.league.domain.User;
import com.example.league.dto.RequestTeamDto;
import com.example.league.dto.TeamDto;
import com.example.league.exception.DuplicateTeamNameException;
import com.example.league.exception.NotMatchingTeamNameException;
import com.example.league.repository.TeamRepository;
import com.example.league.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class TeamServiceImplTest {



    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MemberService memberService;


    @Test
    void createTeamSuccess(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId = userRepository.save(user).getId();

        Long teamId = teamService.createTeam("상명", 10, teamFounderId);

        Team team = teamRepository.findById(teamId).get();

        assertThat(team.getMaxSize()).isEqualTo(10);
        assertThat(team.getEmail()).isEqualTo(user.getEmail());
        assertThat(team.getUserList().size()).isEqualTo(1);
    }

    @Test
    void createTeamFailDuplicateTeamName(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        User user2 = getTeamFounder2(address);
        Long teamFounderId2 = userRepository.save(user2).getId();
        Long teamId = teamService.createTeam("상명", 10, teamFounderId1);

        assertThatThrownBy(() -> teamService.createTeam("상명",10,teamFounderId2))
                .isInstanceOf(DuplicateTeamNameException.class);
    }
    @Test
    void DeleteTeam(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        Long teamId = teamService.createTeam("상명", 10, teamFounderId1);

        teamService.deleteTeam(teamId);

        assertThatThrownBy(() ->teamRepository.findById(teamId).orElseThrow(
                () ->
                {
                    throw new IllegalStateException("없는 팀입니다.");
                }
        )).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void updateTeam(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        Long teamId1 = teamService.createTeam("상명", 10, teamFounderId1);
        User user2 = getTeamFounder2(address);
        Long teamFounderId2 = userRepository.save(user2).getId();
        Long teamId2 = teamService.createTeam("국민", 10, teamFounderId1);

        Long updateId = teamService.updateTeam("경복", 10, teamId2, teamFounderId2);
        Team team = teamRepository.findById(updateId).get();

        assertThat(team.getEmail()).isEqualTo(user2.getEmail());
        assertThat(team.getName()).isEqualTo("경복");
    }

    @Test
    void updateTeamFailDuplicateName(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        Long teamId1 = teamService.createTeam("상명", 10, teamFounderId1);
        User user2 = getTeamFounder2(address);
        Long teamFounderId2 = userRepository.save(user2).getId();
        Long teamId2 = teamService.createTeam("국민", 10, teamFounderId2);

        assertThatThrownBy(() -> teamService.updateTeam("상명", 10, teamId2, teamFounderId1))
                .isInstanceOf(DuplicateTeamNameException.class);
    }

    @Test
    void acceptRequest(){
        Address address = getAddress();
        User user1 = getTeamFounder1(address);
        Long teamFounderId = userRepository.save(user1).getId();
        Long teamId = teamService.createTeam("상명", 10, teamFounderId);
        User user2 = getMember(address);
        Long memberId = userRepository.save(user2).getId();
        Long requestTeamId = memberService.requestTeam(teamId, memberId);
        teamService.acceptMember(requestTeamId);
        Team team = teamRepository.findById(teamId).get();
        User resultUser = userRepository.findById(memberId).get();
        assertThat(resultUser.getTeam().getId()).isEqualTo(teamId);
        assertThat(resultUser.getTeam()).isEqualTo(team);
        assertThat(resultUser.getTeam().getNowSize()).isEqualTo(1);
    }

    @Test
    void findRequest(){
        Address address = getAddress();
        User user1 = getTeamFounder1(address);
        Long teamFounderId = userRepository.save(user1).getId();
        Long teamId = teamService.createTeam("상명", 10, teamFounderId);
        User user2 = getMember(address);
        Long memberId = userRepository.save(user2).getId();
        Long requestTeamId = memberService.requestTeam(teamId, memberId);

        List<RequestTeamDto> result = teamService.findRequestList(teamId);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).MemberName).isEqualTo("kim");
    }
    @Test
    void findRestTeamSeat(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        Long teamId1 = teamService.createTeam("상명", 1, teamFounderId1);
        User user2 = getTeamFounder2(address);
        Long teamFounderId2 = userRepository.save(user2).getId();
        Long teamId2 = teamService.createTeam("국민", 10, teamFounderId2);
        User user3 = getMember(address);
        Long memberId = userRepository.save(user3).getId();
        Long requestTeamId = memberService.requestTeam(teamId1, memberId);
        teamService.acceptMember(requestTeamId);

        List<TeamDto.RestTeamDto> result = teamService.findRestTeamSeat();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getTeamName()).isEqualTo("국민");
    }

    @Test
    void findTeamByNameSuccess(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        Long teamId1 = teamService.createTeam("상명", 10, teamFounderId1);
        TeamDto.RestTeamDto result = teamService.findTeamByName("상명");

        assertThat(result.getTeamName()).isEqualTo("상명");
        assertThat(result.getMaxSize()).isEqualTo(10);
        assertThat(result.getNowSize()).isEqualTo(0);
    }

    @Test
    void findTeamByNameFailNotExistName(){
        Address address = getAddress();
        User user = getTeamFounder1(address);
        Long teamFounderId1 = userRepository.save(user).getId();
        Long teamId1 = teamService.createTeam("상명", 10, teamFounderId1);

        assertThatThrownBy(() -> teamService.findTeamByName("국민"))
                .isInstanceOf(NotMatchingTeamNameException.class);
    }


    private Address getAddress() {
        return Address.builder()
                .street("s")
                .city("a")
                .build();
    }
    private User getTeamFounder1(Address address) {
        return User.createUser("rkdlem48", "asdf", "kim", address, ROLE.TEAM_FOUNDER);
    }
    private User getTeamFounder2(Address address){
        return User.createUser("rkdlem49", "asdf", "kim", address, ROLE.TEAM_FOUNDER);
    }
    private User getMember(Address address){
        return User.createUser("rkdlem48", "asdf", "kim", address, ROLE.MEMBER);
    }
}