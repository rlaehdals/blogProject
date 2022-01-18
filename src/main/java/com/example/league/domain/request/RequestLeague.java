package com.example.league.domain.request;

import com.example.league.domain.League;
import com.example.league.domain.Team;
import com.example.league.domain.auditing.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestLeague extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "request_league_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @Column(name = "request_league")
    private Boolean request;

    //연관 관계 메소드
    private void setLeague(League league){
        this.league=league;
    }

    private void setTeam(Team team){
        this.team=team;
    }

    private void addRequestLeague(){
        this.team.getRequestLeagueList().add(this);
        this.league.getRequestLeagueList().add(this);
    }

    //생성 메소드
    public static RequestLeague createRequestLeague(Team team, League league){
        RequestLeague requestLeague = new RequestLeague();
        requestLeague.request=false;
        requestLeague.setLeague(league);
        requestLeague.setTeam(team);
        requestLeague.addRequestLeague();
        return requestLeague;
    }

    //비즈니스 메소드
    public void acceptRequest(){
        request=true;
        league.setLeague(team);
        league.addTeam(team);
    }

    public void increaseLeagueSize(){
        league.increaseSize();
    }

    public void removeRequest(){
        league.getRequestLeagueList().remove(this);
        team.getRequestLeagueList().remove(this);
    }
}
