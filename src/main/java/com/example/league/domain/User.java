package com.example.league.domain;

import com.example.league.domain.request.RequestTeam;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private League league;

    @OneToMany(mappedBy = "user")
    private List<RequestTeam> requestTeamList =new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private ROLE role;

    @Embedded
    private Address address;

    // 연관 관계 메소드
    public void setTeam(Team team){
        this.team=team;
    }

    public void setLeague(League league){
        this.league=league;
    }

    // 생성 메소드

    public static User createUser(String email, String password, String name, Address address, ROLE role){
        User user = new User();
        user.email=email;
        user.role=role;
        user.password=password;
        user.address=address;
        user.name=name;
        return user;
    }

    // 비즈니스 로직 추가

    public void removeTeam(){
        this.team.decrease(this);
        this.team=null;
    }
}
