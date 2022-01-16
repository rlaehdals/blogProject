package com.example.league.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestTeamDto {
    public String teamName;
    public Integer maxSize;
    public Integer nowSize;
}
