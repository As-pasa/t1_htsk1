package com.as_pasa.t1_htsk1.aspects;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Order(1)
public class LoggingAspectMultiple {
    private Logger logger;

    @Autowired
    public LoggingAspectMultiple(Logger logger) {
        this.logger = logger;
    }

    @Before("execution(* com.as_pasa.t1_htsk1.services..*(..)) && !@annotation(com.as_pasa.t1_htsk1.annotations.IgnoreLogging)")
    public void beforeExecution(JoinPoint joinPoint) {
        logger.info(String.format("METHOD [%s] ARGS [%s] STARTED", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs())));
    }

    @AfterReturning(pointcut = "execution(* com.as_pasa.t1_htsk1.services..*(..)) && !@annotation(com.as_pasa.t1_htsk1.annotations.IgnoreLogging)", returning = "result")
    public void afterSuccess(JoinPoint joinPoint, Object result) {
        logger.info(String.format("METHOD [%s] ARGS [%s] SUCCESS RESULT [%s]", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()), result.toString()));

    }

    @AfterThrowing(pointcut = "execution(* com.as_pasa.t1_htsk1.services..*(..)) && !@annotation(com.as_pasa.t1_htsk1.annotations.IgnoreLogging)", throwing = "exception")
    public void afterError(JoinPoint joinPoint, Throwable throwable) {
        logger.error(String.format("ERROR METHOD [%s] ARGS [%s] EXCEPTION [%s]", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()), throwable.getMessage()));
    }
}
