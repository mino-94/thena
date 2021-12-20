package com.bidding.thena.conf.security.handler;

import com.bidding.thena.svc.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityService securityService;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error(securityService.printStackTrace(exception));

        securityService.failLogin();

        response.getWriter().write(securityService.resultToJson(request, response, exception.getMessage(), null, HttpStatus.UNAUTHORIZED.value()));
    }

}
