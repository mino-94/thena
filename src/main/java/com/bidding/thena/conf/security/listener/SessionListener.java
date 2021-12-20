package com.bidding.thena.conf.security.listener;

import com.bidding.thena.conf.security.vo.Account;
import com.bidding.thena.svc.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;

import static com.bidding.thena.values.Values.SPRING_SECURITY_CONTEXT;

@Slf4j
@Configuration
public class SessionListener implements ApplicationListener {

    @Autowired
    private SecurityService securityService;

    @Value("${spring.application.name}")
    private String application_name;

    @Value("${spring.run.expire.session}")
    private boolean runExpireSession;


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        SecurityContext securityContext = null;

        if (event instanceof SessionDeletedEvent) {
            securityContext = ((SessionDeletedEvent) event).getSession().getAttribute(SPRING_SECURITY_CONTEXT);
        } else if (event instanceof SessionExpiredEvent) {
            securityContext = ((SessionExpiredEvent) event).getSession().getAttribute(SPRING_SECURITY_CONTEXT);
        }

        if (securityContext != null && runExpireSession) {
            Authentication authentication = securityContext.getAuthentication();
            log.info("Principal :: " + authentication.getPrincipal());
            WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) authentication.getDetails();
            log.info("SessionID :: " + webAuthenticationDetails.getSessionId());
            Account account = (Account) authentication.getPrincipal();
            log.info(account.getUserId() + " | " + account.getUserName());

            securityService.successLogout();
        }
    }

}
