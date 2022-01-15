package com.example.league.service;

import com.example.league.domain.Team;
import com.example.league.domain.User;
import com.example.league.domain.request.RequestTeam;
import com.example.league.dto.DuplicateRequestToTeamException;
import com.example.league.dto.RequestTeamDto;
import com.example.league.exception.AlreadyTeamException;
import com.example.league.repository.RequestTeamRepository;
import com.example.league.repository.TeamRepository;
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
public class MemberServiceImpl implements MemberService{
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final RequestTeamRepository requestTeamRepository;


    @Override
    public Long requestTeam(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId).get();
        User user = userRepository.findById(memberId).get();
        RequestTeam requestTeam = RequestTeam.createRequestTeam(user, team);

        if(user.getTeam()!=null){
            throw new AlreadyTeamException("이미 팀을 가지고 있습니다.");
        }
        requestTeamRepository.findByUserId(memberId).ifPresent(
                a -> {
                    throw new DuplicateRequestToTeamException("이미 신청한 기록이 있습니다.");
                }
        );

        return requestTeamRepository.save(requestTeam).getId();
    }

    @Override
    public void WithdrawalTeamRequest(Long memberId, Long teamId) {
        RequestTeam requestTeam = requestTeamRepository.findByMemberIdAndTeamId(memberId, teamId).get();
        requestTeam.removeRequest();
        requestTeamRepository.deleteById(requestTeam.getId());
    }

    @Override
    public void leavingTeam(Long memberId) {
        User user = userRepository.findById(memberId).get();
        user.removeTeam();
    }

    @Override
    public List<RequestTeamDto> findRequestList(Long memberId) {
        return requestTeamRepository.findByMemberId(memberId, Sort.by(Sort.Direction.DESC,"createdTime"))
                .stream().map(a -> new RequestTeamDto(a.getRequest(),a.getTeam().getName())).collect(Collectors.toList());
    }
}
