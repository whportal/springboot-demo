package com.wh.redisson.test;

import com.google.common.truth.Truth;
import com.wh.redisson.entity.User;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedissonTest {

    private static final Logger log = LoggerFactory.getLogger(RedissonTest.class);

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Before
    public void before() {
        log.info("---------------- before ----------------");
    }

    @After
    public void after() {
        log.info("---------------- after ----------------");
    }

    @Test
    public void useGoogleTruth() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        log.info("sum:{}", sum);
        // Truth.assert_().withMessage("just test fail.").fail();
        Truth.assertThat(sum).isGreaterThan(0);
    }

    @Test
    public void testRedissonWired() {
        RAtomicLong atomicLong = redissonClient.getAtomicLong("atomicLong");
        long l = atomicLong.incrementAndGet();
        log.info("result:{}", l);
        Truth.assertThat(l).isEqualTo(1L);
        boolean delete = redissonClient.getAtomicLong("atomicLong").delete();
        MatcherAssert.assertThat(delete, Matchers.is(true));
    }

    @Test
    public void testRedisTemplateWired() {
        String key = "stringRedisTemplate";
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(key, key);
        String value = stringStringValueOperations.get(key);
        MatcherAssert.assertThat(value, Matchers.equalTo(key));
        Boolean delete = stringRedisTemplate.delete(key);
        MatcherAssert.assertThat(delete, Matchers.is(true));
    }

    @Test
    public void testSaveHash() {
        User user = new User();
        user.setUsername("JackMa");
        user.setPassword("123456");
        user.setAge(18);
        user.setBirthday(new Date());
        user.setMoney(200000000000000L);
        RBucket<User> bucket = redissonClient.getBucket("redisson:hash:user");
        bucket.set(user);
        User redissonUser = bucket.get();
        log.info("redissonUser:{}", redissonUser);
        MatcherAssert.assertThat(redissonUser, Matchers.instanceOf(User.class));
        // boolean delete = bucket.delete();
        // MatcherAssert.assertThat(delete, Matchers.is(true));
    }
}
