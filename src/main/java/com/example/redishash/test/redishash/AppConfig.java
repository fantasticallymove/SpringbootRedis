package com.example.redishash.test.redishash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {

    /**
     * Type safe representation of application.properties
     */
    @Autowired
    ClusterConfigurationProperties clusterProperties;

    public @Bean
    RedisConnectionFactory connectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterProperties.getNodes());
        redisClusterConfiguration.setMaxRedirects(3);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    public @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> objectObjectRedisTemplate = new RedisTemplate<>();
        objectObjectRedisTemplate.setDefaultSerializer(new StringRedisSerializer());
        objectObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return objectObjectRedisTemplate;
    }
}
