package com.bidding.thena.conf.security;

import com.bidding.thena.conf.security.handler.CustomAccessDeniedHandler;
import com.bidding.thena.conf.security.handler.CustomLoginFailHandler;
import com.bidding.thena.conf.security.handler.CustomLoginSuccessHandler;
import com.bidding.thena.conf.security.handler.CustomLogoutSuccessHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.firewall.DefaultHttpFirewall;

import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties("spring.security.conf")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    private CustomLoginFailHandler customLoginFailHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    private String[] delCookies;

    private String defaultPath;

    private String loginPath;

    private String expiredPath;

    private String loginProcessPath;

    private String logoutProcessPath;

    private int maxSession;

    private boolean preventLogin;


    @PostConstruct
    private void init() {
        customLoginSuccessHandler.setTargetPath(defaultPath);
        customLogoutSuccessHandler.setTargetPath(loginPath);
        customAccessDeniedHandler.setTargetPath(loginPath);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web.httpFirewall(new DefaultHttpFirewall()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        sessionConfig(http);
        loginConfig(http);
        logoutConfig(http);
        exceptionConfig(http);
        filterConfig(http);
        filterConfig(http);
        csrfConfig(http);
        rememberMeConfig(http);
    }

    private void sessionConfig(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionFixation().changeSessionId()
            .maximumSessions(maxSession)
            .maxSessionsPreventsLogin(preventLogin)
            .expiredUrl(expiredPath);
    }

    private void loginConfig(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage(loginProcessPath)
            .successHandler(customLoginSuccessHandler)
            .failureHandler(customLoginFailHandler);
    }

    private void logoutConfig(HttpSecurity http) throws Exception {
        http.logout()
            .logoutUrl(logoutProcessPath)
            .logoutSuccessHandler(customLogoutSuccessHandler)
            .deleteCookies(delCookies);
    }

    private void exceptionConfig(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
    }

    private void filterConfig(HttpSecurity http) {
        http.addFilterAt(customFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    private void csrfConfig(HttpSecurity http) throws Exception {
        http.csrf();
    }

    private void rememberMeConfig(HttpSecurity http) throws Exception {
        http.rememberMe().disable();
    }

}
