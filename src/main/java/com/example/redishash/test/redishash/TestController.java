package com.example.redishash.test.redishash;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {
    //處理寫用的 redis
    private final PersonRepository repo;
    private final AtomicInteger index = new AtomicInteger(0);

    @Qualifier(value = "read")
    private final RedisTemplate<String, Object> readTemplate;

    @Autowired
    public TestController(PersonRepository repo, RedisTemplate<String, Object> readTemplate) {
        this.readTemplate = readTemplate;
        this.repo = repo;
    }

    /**
     * 使用第一台主機寫
     *
     * @return
     */
    @GetMapping(value = "/redisHash")
    public String testRedis(HttpServletRequest request) {
        Person person = new Person();
        try {
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = request.getReader().readLine()) != null) {
                sb.append(text);
            }
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            person.setName(jsonObject.getString("name") + index.incrementAndGet());
            person.setAge(jsonObject.getIntValue("age"));
            System.out.println(JSONObject.toJSONString(repo.save(person)));
        } catch (Exception e) {
            return "exception";
        }
        return JSONObject.toJSONString(person);
    }

    /**
     * 使用第二台從機讀
     *
     * @return
     */
    @GetMapping(value = "/get")
    public String get() {
        List<Person> personList = new ArrayList<>();
        Iterator<Object> person = readTemplate.opsForSet().members("person").iterator();
        while (person.hasNext()) {
            String key = (String) person.next();
            List<Object> values = readTemplate.opsForHash().values("person:" + key);
            Person e = new Person();
            e.setAge(Integer.parseInt(values.get(1).toString()));
            e.setName(values.get(2).toString());
            personList.add(e);
        }
        return JSONObject.toJSONString(personList);
    }
}
