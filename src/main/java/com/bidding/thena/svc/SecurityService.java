package com.bidding.thena.svc;

import com.bidding.thena.conf.security.vo.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static com.bidding.thena.values.Values.ROLE;

@Service
public class SecurityService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;


    public Map<RequestMatcher, List<ConfigAttribute>> getAllAuthorities() {
        Map<RequestMatcher, List<ConfigAttribute>> resources = new LinkedHashMap<>();

        // Authoriz -> CG0205
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        configAttributes.add(new SecurityConfig(ROLE + "ADMIN"));

        resources.put(new AntPathRequestMatcher("/api/detail/admin"), configAttributes);

        configAttributes = new ArrayList<>();
        configAttributes.add(new SecurityConfig(ROLE + "USER"));

        resources.put(new AntPathRequestMatcher("/api/detail/user"), configAttributes);

        configAttributes = new ArrayList<>();
        configAttributes.add(new SecurityConfig(ROLE + "MANAGER"));

        resources.put(new AntPathRequestMatcher("/api/detail/manager"), configAttributes);

        return resources;
    }

    public List setAuthority(Account account) {
        List authorities = new ArrayList<>();

        // Authoriz -> CG0203

        if ("admin".equals(account.getUserId())) {
            authorities.add(new SimpleGrantedAuthority(ROLE + "ADMIN"));
        } else if ("user".equals(account.getUserId())) {
            authorities.add(new SimpleGrantedAuthority(ROLE + "USER"));
        } else if ("manager".equals(account.getUserId())) {
            authorities.add(new SimpleGrantedAuthority(ROLE + "MANAGER"));
        }
        return authorities;
    }

    public void successLogin() {

    }

    public void failLogin() {

    }

    public void successLogout() {

    }

    public String resultToJson(HttpServletRequest request, HttpServletResponse response, String msg, String redirect, int code) throws JsonProcessingException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(code);

        Map result = new HashMap<>();
        result.put("msg", msg);
        result.put("redirect", redirect);

        return objectMapper.writeValueAsString(getAuthResult(request, result));
    }

    public Map getAuthResult(HttpServletRequest request, Object obj) {
        Map result = new HashMap<>();
        result.put("result", obj);
        result.put("authentication", getAuth());
        result.put("xsrfToken", ((CsrfToken) request.getAttribute(CsrfToken.class.getName())).getToken());

        return result;
    }

    public String printStackTrace(Exception e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    private Map getAuth() {
        Map auth = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            auth = objectMapper.convertValue(authentication.getPrincipal(), Map.class);
            auth.remove("userId");
        }
        return auth;
    }

}
