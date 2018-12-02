package com.mtr.web;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    Logger logger = LoggerFactory.getLogger(TestAspect.class);

    @Before("execution(* com.mtr..*Controller.*(..))")
    public void handleBefore(JoinPoint joinPoint){
        logger.info("===============> on before ");
    }

    @After("execution(* com.mtr..*Controller.*(..))")
    public void handleAfter(JoinPoint joinPoint){
        logger.info("===============> on After ");
    }
}
