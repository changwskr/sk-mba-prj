package com.sk.mba.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.sk.mba..service..*(..)) || execution(* com.sk.mba..controller..*(..)) || execution(* com.sk.mba..repository..*(..))")
    public Object logMethodStartAndEnd(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className + "." + methodName + " 시작");
        try {
            Object result = joinPoint.proceed();
            System.out.println(className + "." + methodName + " 종료");
            return result;
        } catch (Throwable t) {
            System.out.println(className + "." + methodName + " 종료(예외 발생)");
            throw t;
        }
    }
} 