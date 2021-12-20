package com.bidding.thena.conf.security.handler;

import com.bidding.thena.svc.SecurityService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Slf4j
@Component
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

    @Autowired
    private SecurityService securityService;

    private String targetPath;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        String exceptionName = exception.getClass().getSimpleName();

        if ("AccessDeniedException".equals(exceptionName) || "InvalidCsrfTokenException".equals(exceptionName)) {
            log.error(securityService.printStackTrace(exception));

            if ("AccessDeniedException".equals(exceptionName)) {
                response.getWriter().write(securityService.resultToJson(request, response, "Access Denied", null, HttpStatus.FORBIDDEN.value()));
            } else {
                response.getWriter().write(securityService.resultToJson(request, response, "Invalid Token", null, HttpStatus.FORBIDDEN.value()));
            }
        } else { // MissingCsrfTokenException
            response.getWriter().write(securityService.resultToJson(request, response, null, targetPath, HttpStatus.OK.value()));
        }
    }

}
