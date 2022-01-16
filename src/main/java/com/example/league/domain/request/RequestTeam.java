package com.example.league.domain.request;

import com.example.league.domain.Team;
import com.example.league.domain.User;
import com.example.league.domain.auditing.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestTeam extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "request_team")
    private Boolean request;


    // 연관 관계 메소드
    private void setUser(User user) {
        this.user = user;
    }

    private void setTeam(Team team) {
        this.team = team;
    }

    private void addRequestTeam() {
        user.getRequestTeamList().add(this);
        team.getRequestTeamList().add(this);
    }

    // 생성 메소드
    public static RequestTeam createRequestTeam(User user, Team team) {
        RequestTeam requestTeam = new RequestTeam();
        requestTeam.request = false;
        requestTeam.setTeam(team);
        requestTeam.setUser(user);
        requestTeam.addRequestTeam();
        return requestTeam;
    }


    // 비즈니스 로직 추가
    public void removeRequest(){
        user.getRequestTeamList().remove(this);
        team.getRequestTeamList().remove(this);
    }

    public void acceptRequest(){
        request=true;
        team.setUser(user);
        team.addTeam(user);
        team.increaseSize();
    }


}
