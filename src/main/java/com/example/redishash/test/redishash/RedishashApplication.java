package com.example.redishash.test.redishash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @EnableRedisRepositories 啟動CRUDRepository 的啟動註解,表示支持redis 基本配置並且可以使用介面操作redis
 */
@SpringBootApplication
@EnableRedisRepositories
public class RedishashApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedishashApplication.class, args);
    }
}
