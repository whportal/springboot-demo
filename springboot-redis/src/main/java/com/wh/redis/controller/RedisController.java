package com.wh.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/check")
    public void checkRedisTemplate() {
        System.out.println("redisTemplate = " + redisTemplate);
    }
}
