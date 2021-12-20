package com.bidding.thena.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import static com.bidding.thena.values.Values.EXPIRE_TIME_SEC;

@EnableAspectJAutoProxy
@EnableDiscoveryClient
@EnableWebSecurity
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = EXPIRE_TIME_SEC, redisNamespace = "${spring.application.name}:index")
@SpringBootApplication(scanBasePackages = "com.bidding.thena")
public class Run {


    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}
