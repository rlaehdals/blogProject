package com.example.league.advice;

import com.example.league.controller.LeagueController;
import com.example.league.dto.ErrorDto;
import com.example.league.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = LeagueController.class)
public class LeagueControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto alreadyExistLeague(AlreadyLeagueException e){
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto alreadyAccept(AlreadyAcceptTeamException e){
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto notExistLeagueName(NotExistLeagueNameException e){
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto duplicateLeagueName(DuplicateLeagueNameException e){
        return new ErrorDto(e.getMessage());
    }
}

