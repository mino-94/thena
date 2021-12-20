package com.bidding.thena.values;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface Values {

    Map<RequestMatcher, List<ConfigAttribute>> RESOURCES = new LinkedHashMap<>();

    String RESOURCE_HANDLER_PATH = "/**/*";

    String RESOURCE_LOCATIONS_PATH = "classpath:/static/";

    String DEFAULT_RESOURCE_PATH = "/static/index.html";

    String DEFAULT_PATH = "/api";

    String ROLE = "ROLE_";

    String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

    int EXPIRE_TIME_SEC = 10;

}
