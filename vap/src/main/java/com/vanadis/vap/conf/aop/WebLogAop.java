package com.vanadis.vap.conf.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @description: controller请求日志
 * @author: Created by 遥远 on 2019-03-24 03:00
 */

@Slf4j
@Aspect
@Component
public class WebLogAop {

    @Pointcut("execution(public * com.vanadis.vap.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 接收到请求，记录请求内容
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("[Vap.Request] {URL : {}, HTTP_METHOD : {}, IP : {}, CLASS_METHOD : {}.{}, ARGS : {}}",
                request.getRequestURL().toString(),
                request.getMethod(),
                request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        log.info("[Vap.Response] : {}", ret);
    }
}
