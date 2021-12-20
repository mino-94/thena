package com.bidding.thena.conf.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.annotation.PostConstruct;

@Data
@Configuration
public class RedisConfig {

    @Autowired
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;

    @Autowired
    private RedisTemplate redisTemplate;


    @PostConstruct
    private void init() {
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(findByIndexNameSessionRepository);
    }

}
