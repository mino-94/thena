package com.bidding.thena.conf.security;

import com.bidding.thena.svc.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.bidding.thena.values.Values.RESOURCES;


@Component
public class CustomFilterInvocationMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SecurityService securityService;


    @PostConstruct
    private void init() {
        validResources();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        validResources();

        for (Map.Entry<RequestMatcher, List<ConfigAttribute>> resource : RESOURCES.entrySet()) {
            if (resource.getKey().matches(request)) {
                return resource.getValue();
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    private void validResources() {
        if (CollectionUtils.isEmpty(RESOURCES)) {
            RESOURCES.putAll(securityService.getAllAuthorities());
        }
    }

}
