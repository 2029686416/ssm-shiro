package com.demons.shiro.annotation.test02;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//切面类
@Aspect
@Component("smsAndMailSenderMonitor")
public class SMSAndMailSenderMonitor {

private Logger logger = LoggerFactory.getLogger(SMSAndMailSenderMonitor.class);


/**
 * 在所有标记了@SMSAndMailSender的方法中切入
 * @param joinPoint
 * @param result
 */
@AfterReturning(value="@annotation(com.demons.shiro.annotation.test02.SMSAndMailSender)", returning="result")//有注解标记的方法，执行该后置返回
public void afterReturning(JoinPoint joinPoint , Object result) {//注解标注的方法返回值
	System.out.println("-------->2");
	MethodSignature ms = (MethodSignature) joinPoint.getSignature();
    Method method = ms.getMethod();
    boolean active = method.getAnnotation(SMSAndMailSender.class).isActive();
    if (!active) {
        return;
    }
    String smsContent = method.getAnnotation(SMSAndMailSender.class).smsContent();
    String mailContent = method.getAnnotation(SMSAndMailSender.class).mailContent();
    String subject = method.getAnnotation(SMSAndMailSender.class).subject();
   
}



/**
 * 在抛出异常时使用
 * @param joinPoint
 * @param ex
 */
@AfterThrowing(value="@annotation(com.demons.shiro.annotation.test02.SMSAndMailSender)",throwing = "ex")
public void afterThrowing(JoinPoint joinPoint, Throwable ex) {//注解标注的方法抛出的异常
    System.out.println("-------->1");
	MethodSignature ms = (MethodSignature) joinPoint.getSignature();
    Method method = ms.getMethod();
    String subject = method.getAnnotation(SMSAndMailSender.class).subject();
    
}

}
