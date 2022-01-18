package com.example.league.repository;

import com.example.league.domain.request.RequestLeague;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestLeagueRepository extends JpaRepository<RequestLeague, Long> {

    @Query("select re from RequestLeague re join fetch re.team where re.team.id= :teamId")
    Optional<RequestLeague> findByTeamId(@Param("teamId") Long teamId);

    @Query(" select re from RequestLeague re join fetch re.team join fetch re.league where re.team.id= :teamId and " +
            "re.league.id= :leagueId")
    Optional<RequestLeague> findByMemberIdAndTeamId(@Param("teamId") Long teamId, @Param("leagueId") Long leagueId);

    @Query("select re from RequestLeague re join fetch re.team where re.id= :requestId")
    Optional<RequestLeague> findWithFetchJoinUserById(@Param("requestId")Long requestId);

    @Query("select re from RequestLeague  re join fetch re.league join fetch re.team where re.league.id= :leagueId")
    List<RequestLeague> findByLeagueId(@Param("leagueId") Long leagueId);
}
