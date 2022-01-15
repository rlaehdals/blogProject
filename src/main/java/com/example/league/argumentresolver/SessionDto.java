package com.example.league.argumentresolver;

import com.example.league.domain.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionDto {
    private String email;
    private Long id;
    private String name;
    private ROLE role;
}
