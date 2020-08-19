package com.wh.redis.test;

import com.google.gson.Gson;
import com.wh.redis.BaseTest;
import com.wh.redis.entity.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

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
public class RedisTemplateTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(RedisTemplateTest.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSaveString() {
        redisTemplate.opsForValue().set("TEST:USER:STRING", "JackMa");
        String stringValue = (String) redisTemplate.opsForValue().get("TEST:USER:STRING");
        MatcherAssert.assertThat(stringValue, Matchers.equalTo("JackMa"));
    }

    @Test
    public void testSaveLong() {
        // redis 直接存储 long 类型在反序列化时会被当做 integer 处理，造成类型转换异常
        redisTemplate.opsForValue().set("TEST:USER:LONG", "10L");
        String longValue = (String) redisTemplate.opsForValue().get("TEST:USER:LONG");
        MatcherAssert.assertThat(longValue, Matchers.is("10L"));
    }

    @Test
    public void testSaveInteger() {
        redisTemplate.opsForValue().set("TEST:USER:INTEGER", 10);
        int intValue = (int) redisTemplate.opsForValue().get("TEST:USER:INTEGER");
        MatcherAssert.assertThat(intValue, Matchers.is(10));
    }

    @Test
    public void testSaveBoolean() {
        redisTemplate.opsForValue().set("TEST:USER:BOOLEAN", false);
        boolean booleanValue = (boolean) redisTemplate.opsForValue().get("TEST:USER:BOOLEAN");
        MatcherAssert.assertThat(booleanValue, Matchers.is(false));
    }

    @Test
    public void testHash() {
        User user = new User();
        user.setUsername("JackMa");
        user.setPassword("123456");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setMoney(200000000000000L);
        HashOperations<String, String, User> stringObjectObjectHashOperations = redisTemplate.opsForHash();
        stringObjectObjectHashOperations.put("TEST:USER:HASH", "007", user);
        User result = stringObjectObjectHashOperations.get("TEST:USER:HASH", "007");
        System.out.println("result = " + result);
    }

    @Test
    public void testStringHash() {
        User user = new User();
        user.setUsername("JackMa");
        user.setPassword("123456");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setMoney(200000000000000L);

        // 保存对象 到 string 类型（会被序列化为 JSON 存储）
        redisTemplate.opsForValue().set("TEST:USER:STRING:HASH", user);
        Object result = redisTemplate.opsForValue().get("TEST:USER:STRING:HASH");
        MatcherAssert.assertThat(result, Matchers.notNullValue());
        MatcherAssert.assertThat(result, Matchers.instanceOf(User.class));
        // 将取到的数据进行类型转换
        User resultUser = (User) result;

        // 比较原始值与取到的值
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        String resultUserJson = gson.toJson(resultUser);
        log.info("userJson:{}",userJson);
        MatcherAssert.assertThat(userJson, Matchers.equalTo(resultUserJson));
    }
}
