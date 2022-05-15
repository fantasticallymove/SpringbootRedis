package com.example.redishash.test.redishash;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RedisService {
    private AtomicInteger index = new AtomicInteger();
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getKey(String key) {
        Set<Object> value = redisTemplate.opsForSet().members(key);
        return JSONObject.toJSONString(value);
    }

    public Long setKey(JSONObject jsonObject) {
        return redisTemplate.opsForSet().add(jsonObject.getString("name") + index.getAndIncrement()
                , JSONObject.toJSONString(jsonObject));
    }
}
