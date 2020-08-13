package com.wh.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/12
 */
@Configuration
public class CommonConfig {

    /**
     * protobuf 序列化 配置该 Bean 后 Controller 响应数据时会使用 protobuf 序列化
     */
    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    /**
     * 配置 RestTemplate 支持 protobuf
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().messageConverters(protobufHttpMessageConverter()).build();
    }
}
