package com.example.league.repository;

import com.example.league.domain.request.RequestTeam;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestTeamRepository extends JpaRepository<RequestTeam, Long> {

    @Query("select re from RequestTeam re join fetch re.user where re.user.id= :memberId")
    Optional<RequestTeam> findByUserId(@Param("memberId") Long memberId);

    @Query(" select re from RequestTeam re join fetch re.user join fetch re.team where re.user.id=:memberId and re.team.id=:teamId")
    Optional<RequestTeam> findByMemberIdAndTeamId(@Param("memberId") Long memberId, @Param("teamId") Long teamId);

    @Query("select re from RequestTeam  re join fetch re.user join fetch re.team where re.user.id= :memberId")
    List<RequestTeam> findByMemberId(@Param("memberId") Long memberId, Sort sort);
}
