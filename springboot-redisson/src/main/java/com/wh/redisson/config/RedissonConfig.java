package com.wh.redisson.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/21
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    private static final String PREFIX = "redis://";

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        ObjectMapper objectMapper = getObjectMapper();
        config.setCodec(new JsonJacksonCodec(objectMapper));
        String url = PREFIX + host + ":" + port;
        config.useSingleServer().setAddress(url)
                .setDatabase(0);
        return Redisson.create(config);
    }

    private ObjectMapper getObjectMapper() {
        // value 的序列化使用 json 序列化
        ObjectMapper objectMapper = new ObjectMapper();
        // 使用 JSR310 提供的时间序列化类,里面包含了大量的 JDK8 时间序列化类,使其支持 JDK8 时间类，如 LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        // 日期类型不保存为 时间戳
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

}
