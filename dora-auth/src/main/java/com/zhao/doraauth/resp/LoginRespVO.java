package com.zhao.doraauth.resp;

/**
 * @ClassName: LoginRespVO
 * @Author: zhaolianqi
 * @Date: 2021/9/23 16:09
 * @Version: v1.0
 */
public class LoginRespVO {

    private String refreshToken;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
