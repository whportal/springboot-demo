package com.wh.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 自定义 starter 的配置类
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/24
 */
@ConfigurationProperties(prefix = "wh-portal")
public class WhPortalProperties {

    private static final String DEFAULT_NAME = "wh-portal";
    private static final String DEFAULT_MSG = "水星记";
    private String name = DEFAULT_NAME;
    private String msg = DEFAULT_MSG;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
