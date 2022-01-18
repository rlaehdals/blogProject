package com.example.league.advice;

import com.example.league.controller.MemberController;
import com.example.league.dto.ErrorDto;
import com.example.league.exception.AlreadyTeamException;
import com.example.league.exception.DuplicateRequestToTeamException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto alreadyTeam(AlreadyTeamException e){
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto duplicateRequest(DuplicateRequestToTeamException e){
        return new ErrorDto(e.getMessage());
    }
}
