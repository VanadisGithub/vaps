package com.vanadis.vap.conf.aop;

import com.github.jsonzou.jmockdata.JMockData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-26 10:09
 */
@Slf4j
@Aspect
@Component
public class ReturnAop {

    @Pointcut("@annotation(com.vanadis.vap.common.VapAnnotation)")
    public void annotation() {
    }

    @AfterReturning(pointcut = "annotation()", returning = "result")
    public void returnR(JoinPoint joinPoint, Object result) throws IllegalAccessException, InvocationTargetException {
        Class returnClass = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        BeanUtils.copyProperties(result, JMockData.mock(returnClass));
        log.info(result.toString());
    }
}
