package com.bidding.thena.conf.security.handler;

import com.bidding.thena.svc.SecurityService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Slf4j
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private SecurityService securityService;

    private String targetPath;


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (authentication != null) {
            log.info("Logout Detail=[" + authentication.getDetails() + "], Account=[" + authentication.getPrincipal() + "], Roles=" + authentication.getAuthorities());
        }
        response.getWriter().write(securityService.resultToJson(request, response, "Logout Success", targetPath, HttpStatus.OK.value()));
    }

}
