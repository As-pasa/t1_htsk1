package com.as_pasa.t1_htsk1.aspects;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LoggingAspectAround {

    private final Logger logger;

    @Autowired
    public LoggingAspectAround(Logger logger) {
        this.logger = logger;
    }

    @Around("execution(* com.as_pasa.t1_htsk1.services..*(..)) && !@annotation(com.as_pasa.t1_htsk1.annotations.IgnoreLogging)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            logger.info(String.format("METHOD [%s] ARGS [%s] STARTED", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs())));
            Object result = joinPoint.proceed();
            logger.info(String.format("METHOD [%s] ARGS [%s] SUCCESS RESULT [%s]", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()), result.toString()));
            return result;
        } catch (Throwable throwable) {
            logger.error(String.format("ERROR METHOD [%s] ARGS [%s] EXCEPTION [%s]", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()), throwable.getMessage()));
            throw throwable;
        }

    }
}
