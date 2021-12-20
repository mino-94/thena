package com.bidding.thena.conf.security;

import com.bidding.thena.conf.security.vo.Account;
import com.bidding.thena.svc.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SecurityService securityService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // ID(principal) -> ha0102

        if ("5555".equals(authentication.getPrincipal()) && !"5555".equals(authentication.getCredentials())) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
        Account account = new Account("corp", authentication.getPrincipal(), "test", "AD", "ADMIN");

//        if (usedSessions >= 10) {
            // 동접 초과
//        }
        List authorities = securityService.setAuthority(account);

        return new UsernamePasswordAuthenticationToken(account, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

}
