package ru.gb.springAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {

    @Pointcut("within(@ru.gb.springAOP.Timer *)")
    public void beansMethod() {
    }

    @Pointcut("@annotation(ru.gb.springAOP.Timer)")
    public void beansWithAnnotation() {
    }

    @Around("beansMethod() || beansWithAnnotation()")
    public Object loggable(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long deltaTime = System.currentTimeMillis() - startTime;

        System.out.println("Метод " + joinPoint.getTarget().getClass().getSimpleName() + " выполнился за " +
                deltaTime + " миллисекунд.");
        return result;
    }
}
