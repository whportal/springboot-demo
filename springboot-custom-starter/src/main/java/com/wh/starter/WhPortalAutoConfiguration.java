package com.wh.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/24
 */
@Configuration
@EnableConfigurationProperties(WhPortalProperties.class)
@ConditionalOnClass(WhPortalService.class)
public class WhPortalAutoConfiguration {

    private WhPortalProperties whPortalProperties;

    @Autowired
    public WhPortalAutoConfiguration(WhPortalProperties whPortalProperties) {
        this.whPortalProperties = whPortalProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public WhPortalService whPortalService() {
        WhPortalService whPortalService = new WhPortalService();
        whPortalService.setName(whPortalProperties.getName());
        whPortalService.setMsg(whPortalProperties.getMsg());
        return whPortalService;
    }
}
