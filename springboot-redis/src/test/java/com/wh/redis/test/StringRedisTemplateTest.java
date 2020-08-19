package com.wh.redis.test;

import com.google.gson.Gson;
import com.wh.redis.BaseTest;
import com.wh.redis.entity.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
public class StringRedisTemplateTest extends BaseTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testHash() {
        User user = new User();
        user.setUsername("JackMa");
        user.setPassword("123456");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setMoney(200000000000000L);
        Gson gson = new Gson();
        redisTemplate.opsForValue().set("STRING:USER:HASH", user);
        Object value = redisTemplate.opsForValue().get("STRING:USER:HASH");
        System.out.println("value = " + value);
        // MatcherAssert.assertThat(value, Matchers.equalTo(value));
    }
}
