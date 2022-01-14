package com.example.league.domain;

import com.example.league.domain.auditing.BaseEntity;
import com.example.league.domain.request.RequestLeague;
import com.example.league.domain.request.RequestTeam;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Column(name = "team_max_size")
    private Integer maxSize;

    @Column(name = "founder_email")
    private String email;

    @Column(name = "team_now_size")
    private Integer nowSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id")
    private League league;

    @OneToMany(mappedBy = "team")
    private List<RequestTeam> requestTeamList = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<RequestLeague> requestLeagueList = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<User> userList = new ArrayList<>();


    public void setUser(User user){
        user.setTeam(this);
    }
    public void addTeam(User user){
        userList.add(user);
    }

    public void setLeague(League league){
        this.league=league;
    }

    public static Team createTeam(User user,String name, Integer maxSize){
        Team team = new Team();
        team.name=name;
        team.maxSize=maxSize;
        team.email=user.getEmail();
        team.nowSize=0;
        team.addTeam(user);
        team.setUser(user);
        return team;
    }

}
