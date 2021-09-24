package com.zhao.common.utils;

/**
 * @ClassName: TokenConfig
 * @Author: zhaolianqi
 * @Date: 2021/9/24 11:31
 * @Version: v1.0
 */
public class TokenConfig {

    private String secret;
    private Integer expiration;
    private Integer refreshExp = 604800;

    public Integer getRefreshExp() {
        return refreshExp;
    }

    public void setRefreshExp(Integer refreshExp) {
        this.refreshExp = refreshExp;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
