package com.zhao.dorazuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @ClassName: CustomConfig
 * @Description: TODO
 * @Author: zhaolianqi
 * @Date: 2021/9/17 16:23
 * @Version: v1.0
 */
@Configuration
@EnableConfigurationProperties(CustomConfig.class)
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    private Map<String, Integer> limitUrls;

    public Map<String, Integer> getLimitUrls() {
        return limitUrls;
    }

    public void setLimitUrls(Map<String, Integer> limitUrls) {
        this.limitUrls = limitUrls;
    }
}
