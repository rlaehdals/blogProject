package com.example.league.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ROLE {
    MEMBER,
    TEAM_FOUNDER,
    LEAGUE_HOST
}
