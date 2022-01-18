package com.example.league.domain;

import com.example.league.domain.request.RequestLeague;
import com.example.league.exception.OverMaxLeagueSizeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private Long id;

    @Column(name="league_name")
    private String leagueName;

    @Column(name = "league_scale_size")
    private Integer scaleSize;

    @Column(name= "league_now_size")
    private Integer nowSize;

    @Column(name = "league_host_name")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "league")
    private List<RequestLeague> requestLeagueList = new ArrayList<>();

    @OneToMany(mappedBy = "league")
    private List<Team> teamList = new ArrayList<>();



    //연관 관계 메소드
    private void setUser(User user){
        this.user=user;
    }

    private void setLeague(){
        user.setLeague(this);
    }

    public void addTeam(Team team){
        teamList.add(team);
    }

    public void setLeague(Team team){
        team.setLeague(this);
    }


    //생성 메소드
    public static League createLeague(Integer scaleSize,String leagueName, User user){
        League league = new League();
        league.leagueName=leagueName;
        league.scaleSize=scaleSize;
        league.nowSize=0;
        league.email=user.getEmail();
        league.setUser(user);
        league.setLeague();
        return league;
    }

    //비즈니스 로직
    public void increaseSize(){
        if(scaleSize.equals(nowSize)){
            throw new OverMaxLeagueSizeException("이미 모집이 완료된 리그입니다.");
        }
        nowSize++;
    }
}
