package com.example.redishash.test.redishash;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

    @Value("${redis.read-only.host}")
    private String host;

    @Value("${redis.read-only.port}")
    private int port;

    public RedisConnectionFactory connectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    @Bean(name = "read")
    public RedisTemplate<String, Object> onlyReadRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //請注意這邊要配String 是因為存進去是String 類型的key 沒設置將會啟用預設
        template.setDefaultSerializer(new StringRedisSerializer());
        template.setConnectionFactory(connectionFactory());
        return template;
    }
}
