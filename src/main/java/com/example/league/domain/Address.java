package com.example.league.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String street;

    @Builder
    public Address(String city, String street){
        this.city=city;
        this.street=street;
    }
}

