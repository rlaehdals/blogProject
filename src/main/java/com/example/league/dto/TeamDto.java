package com.example.league.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateOrUpdateTeamDto{
        public String name;
        public int size;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestTeamDto {
        public String teamName;
        public Integer maxSize;
        public Integer nowSize;
    }

}
