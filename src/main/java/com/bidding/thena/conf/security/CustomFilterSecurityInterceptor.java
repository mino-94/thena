package com.bidding.thena.conf.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Data
@Component
@ConfigurationProperties("spring.security.role.conf")
public class CustomFilterSecurityInterceptor extends FilterSecurityInterceptor {

    @Autowired
    private CustomFilterInvocationMetadataSource customFilterInvocationMetadataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String roleHierarchy;


    @PostConstruct
    private void init() {
        super.setSecurityMetadataSource(customFilterInvocationMetadataSource);
        super.setAccessDecisionManager(customAccessDecisionManager());
        super.setAuthenticationManager(authenticationManager);
    }

    private AccessDecisionManager customAccessDecisionManager() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(this.roleHierarchy);

        return new AffirmativeBased(Arrays.asList(new RoleHierarchyVoter(roleHierarchy)));
    }

}
