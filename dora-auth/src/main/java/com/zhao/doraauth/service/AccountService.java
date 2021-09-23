package com.zhao.doraauth.service;

import com.zhao.doraauth.entity.Account;
import com.zhao.doraauth.resp.LoginRespVO;
import com.zhao.dorambg.service.MyBaseService;

/**
 * @ClassName: AccountService
 * @Author: zhaolianqi
 * @Date: 2021/9/23 14:10
 * @Version: v1.0
 */
public interface AccountService extends MyBaseService<Account> {

    interface Type {
        int ACCOUNT_PWD = 1;
        int MINI_PROGRAM = 2;
    }

    LoginRespVO login(Account entity);

    String getAccessTokenByRefreshToken(String refreshToken);

}
