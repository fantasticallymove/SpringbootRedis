package com.example.redishash.test.redishash;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {
    private AtomicInteger index = new AtomicInteger();
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public TestController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 使用第一台主機寫
     *
     * @return
     */
    @GetMapping(value = "/redisHash")
    public String testRedis(HttpServletRequest request) {
        JSONObject jsonObject;
        try {
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = request.getReader().readLine()) != null) {
                sb.append(text);
            }
            jsonObject = JSONObject.parseObject(sb.toString());
            redisTemplate.opsForSet().add(jsonObject.getString("name"), JSONObject.toJSONString(jsonObject));
        } catch (Exception e) {
            return e.getMessage();
        }
        return JSONObject.toJSONString(jsonObject);
    }

    /**
     * 使用第一台主機寫
     *
     * @return
     */
    @GetMapping(value = "/get")
    public String testRedisGet(HttpServletRequest request) {
        JSONObject jsonObject;
        try {
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = request.getReader().readLine()) != null) {
                sb.append(text);
            }
            jsonObject = JSONObject.parseObject(sb.toString());
            Set<Object> joe = redisTemplate.opsForSet().members(jsonObject.getString("key"));
            return JSONObject.toJSONString(joe);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
