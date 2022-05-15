package com.example.redishash.test.redishash;

import com.alibaba.fastjson.JSONObject;
import com.example.redishash.test.redishash.datasource.City;
import com.example.redishash.test.redishash.datasource.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@CacheConfig(cacheNames = "city")
public class RedisService {
    private AtomicInteger index = new AtomicInteger();
    private final RedisTemplate<String, Object> redisTemplate;
    private CityRepository cityRepository;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate, CityRepository cityRepository) {
        this.redisTemplate = redisTemplate;
        this.cityRepository = cityRepository;
    }

    public String getKey(String key) {
        Set<Object> value = redisTemplate.opsForSet().members(key);
        return JSONObject.toJSONString(value);
    }

    public Long setKey(JSONObject jsonObject) {
        return redisTemplate.opsForSet().add(jsonObject.getString("name") + index.getAndIncrement()
                , JSONObject.toJSONString(jsonObject));
    }

    @Cacheable(key = "#p0")
    public City getCityKey(String city) {
        return cityRepository.getFromAndToCity(city);
    }
}
