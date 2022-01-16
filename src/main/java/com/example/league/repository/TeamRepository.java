package com.example.league.repository;

import com.example.league.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t where t.name= :teamName")
    Optional<Team> findByName(@Param("teamName") String teamName);

    @Query("select t from Team  t where t.maxSize>t.nowSize")
    List<Team> findByRestSeat();
}
