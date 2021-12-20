package com.bidding.thena.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Set getKeys(String key) {
        return redisTemplate.keys(key);
    }

    public Set getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public long existkeys(Collection keys) {
        return redisTemplate.countExistingKeys(keys);
    }

    public void deleteKeys(Collection keys) {
        redisTemplate.delete(keys);
    }

    public void deleteSetValue(String key, Object... values) {
        redisTemplate.opsForSet().remove(key, values);
    }

}
