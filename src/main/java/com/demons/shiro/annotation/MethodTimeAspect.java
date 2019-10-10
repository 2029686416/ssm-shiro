package com.demons.shiro.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimeAspect {
    private final static Logger logger = LoggerFactory.getLogger(MethodTimeAspect.class);

    //匹配使用了ExecTime注解的方法(注意是方法)
    @Pointcut("@annotation(com.demons.shiro.annotation.ExecTime)")
    public void pointCut() {
    	System.out.println("oop");
    }

    //环绕通知
    @Around("pointCut()")
    public Object useTime(ProceedingJoinPoint joinPoint) {
        Object out = null;
        try {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            logger.info(String.format(" Method [%s.%s()] start", className, joinPoint.getSignature().getName()));
            long start = System.currentTimeMillis();
            //执行目标函数
            out = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            logger.info(String.format(" Method [%s.%s()] execution time:%sms", className, joinPoint.getSignature().getName(), elapsedTime));
            System.out.println("我拉拉----");
        } catch (Throwable throwable) {
            logger.error("aop record method exec time error", throwable);
        }
        return out;
    }
}
