package com.example.league.advice;

import com.example.league.controller.UserController;
import com.example.league.dto.ErrorDto;
import com.example.league.exception.UserEmailDuplicateException;
import com.example.league.exception.UserEmailOrPasswordWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.league.controller")
public class UserControllerAdvice {

    @ExceptionHandler(UserEmailDuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto duplicateEmail(Exception e){
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto wrongEmailOrPassword(UserEmailOrPasswordWrongException e){
        return new ErrorDto(e.getMessage());
    }
}
