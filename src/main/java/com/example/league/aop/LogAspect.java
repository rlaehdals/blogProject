package com.example.league.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Around("@annotation(com.example.league.aop.MethodLog)")
    public Object logAop(ProceedingJoinPoint joinPoint)throws Throwable{
        Signature signature = joinPoint.getSignature();
        LocalDateTime startTime = LocalDateTime.now();
        String start = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS").format(startTime);
        log.info("[start time]: {} [target]: {} ",start, signature);
        Object result = joinPoint.proceed();
        LocalDateTime endTime = LocalDateTime.now();
        String end = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS").format(startTime);
        log.info("[end time]: {} [target]: {}",end,signature);
        return result;
    }
}
