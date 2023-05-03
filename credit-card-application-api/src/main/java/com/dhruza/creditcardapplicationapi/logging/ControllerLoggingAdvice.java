package com.dhruza.creditcardapplicationapi.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAdvice {
    @Pointcut(value = "execution(* com.dhruza.creditcardapplicationapi.controller.*.*(..))")
    public void controllerPointcut(){}

    @Around("controllerPointcut()")
    public Object controllerLogger(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = getHttpServletRequest();

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        ObjectMapper om = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] args = pjp.getArgs();

        log.info("Resource " + request.getRequestURI() + " requested");
//                "requested by user, " + currentPrincipalName +
//                "(" + om.writeValueAsString(authorities) + ")");

        log.info("Invoked " + className + " : " + methodName + " : " + "args : " + om.writeValueAsString(args));

        Object result = pjp.proceed();

        log.info("Result returned: "  + om.writeValueAsString(result));

        return result;
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }
}
