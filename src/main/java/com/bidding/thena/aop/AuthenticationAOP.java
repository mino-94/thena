package com.bidding.thena.aop;


import com.bidding.thena.svc.SecurityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class AuthenticationAOP {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SecurityService securityService;


    @Around(value = "execution(public java.util.Map com.bidding.thena.ctr..*(..))")
    private Map validAuthentication(ProceedingJoinPoint point) throws Throwable {
        Object obj = point.proceed();
        return securityService.getAuthResult(request, CollectionUtils.isEmpty((Map) obj) ? null : obj);
    }

}
