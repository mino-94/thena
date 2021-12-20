package com.bidding.thena.conf.security.handler;

import com.bidding.thena.svc.SecurityService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Slf4j
@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private SecurityService securityService;

    private String targetPath;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Login Detail=[" + authentication.getDetails() + "], Account=[" + authentication.getPrincipal() + "], Roles=" + authentication.getAuthorities());

        securityService.successLogin();

        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);

        if (savedRequest != null) {
            targetPath = savedRequest.getRedirectUrl();
        }
        response.getWriter().write(securityService.resultToJson(request, response, "Login Success", targetPath, HttpStatus.OK.value()));
    }

}
