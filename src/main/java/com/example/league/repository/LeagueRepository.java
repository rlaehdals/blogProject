package com.example.league.repository;


import com.example.league.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Long> {

    @Query("select l from League l where l.leagueName= :leagueName")
    Optional<League> findByName(@Param("leagueName") String leagueName);

    @Query("select l from League l  where l.scaleSize>l.nowSize")
    List<League> findByRestSeat();
}
