package com.example.league.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestLeagueDto {
    public String LeagueName;
    public Integer scaleSize;
    public Integer nowSize;
}
