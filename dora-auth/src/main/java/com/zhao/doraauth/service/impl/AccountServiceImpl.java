package com.zhao.doraauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhao.common.utils.encrypt.MD5Utils;
import com.zhao.commonservice.constants.ResponseStatus;
import com.zhao.commonservice.entity.TokenModel;
import com.zhao.commonservice.exception.BusinessException;
import com.zhao.commonservice.service.RedisService;
import com.zhao.commonservice.service.UserInfo;
import com.zhao.commonservice.utils.Asserts;
import com.zhao.commonservice.utils.CommonUtils;
import com.zhao.commonservice.utils.JwtTokenUtil;
import com.zhao.doraauth.constants.AuthConstants;
import com.zhao.doraauth.dao.AccountMapper;
import com.zhao.doraauth.entity.Account;
import com.zhao.doraauth.resp.LoginRespVO;
import com.zhao.doraauth.service.AccountService;
import com.zhao.dorambg.service.impl.MyBaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @ClassName: AccountServiceImpl
 * @Author: zhaolianqi
 * @Date: 2021/9/23 14:11
 * @Version: v1.0
 */
@Service
public class AccountServiceImpl extends MyBaseServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private RedisService redisService;
    @Value("${jwt.tokenExp}")
    private Integer tokenExp;
    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public boolean save(Account entity) {
        if (entity.getPwd() != null)
            entity.setPwd(MD5Utils.Bit32(entity.getPwd()));
        return super.save(entity);
    }

    @Override
    public LoginRespVO login(Account entity) {
        Asserts.notNull(entity);
        Asserts.notNull(entity.getType());
        switch (entity.getType()){
            case Type.ACCOUNT_PWD:
                Asserts.notEmpty(entity.getAccount());
                Asserts.notEmpty(entity.getPwd());
                Account account = this.getOne(new QueryWrapper<Account>().eq("account", entity.getAccount()));
                if (!MD5Utils.Bit32(entity.getPwd()).equalsIgnoreCase(account.getPwd()))
                    throw new BusinessException(ResponseStatus.USER_ACCOUNT_OR_PWD_WRONG);

                String sign = CommonUtils.getUUIDStr();
                String refreshToken = JwtTokenUtil.generateRefreshToken(new UserInfo() {
                    @Override
                    public Serializable getId() {
                        return account.getUserId();
                    }
                    @Override
                    public String getSign() {
                        return sign;
                    }
                });
                redisService.putMapCache(AuthConstants.REFRESH_TOKEN_KEY, ""+account.getUserId(), sign, tokenExp);
                logger.info("账号:{} 生成token:{},签名:{}", account.getAccount(), refreshToken, sign);
                LoginRespVO res = new LoginRespVO();
                res.setAccessToken(JwtTokenUtil.generateToken(new UserInfo() {
                    @Override
                    public Serializable getId() { return account.getUserId(); }
                    @Override
                    public String getSign() { return null; }
                }));
                res.setRefreshToken(refreshToken);
                return res;
            case Type.MINI_PROGRAM:
                // TODO 小程序自动登录

                return null;
        }
        return null;
    }

    @Override
    public String getAccessTokenByRefreshToken(String refreshToken) {
        TokenModel tokenModel = JwtTokenUtil.token2tokenModal(refreshToken);
        if (tokenModel == null)
            throw new BusinessException(ResponseStatus.LOGIN_OUT_OF_TIME);
        UserInfo userInfo = tokenModel.getUser();
        if (userInfo.getSign() == null)
            throw new BusinessException(ResponseStatus.LOGIN_OUT_OF_TIME);

        String sign = (String) redisService.getFromMapCache(AuthConstants.REFRESH_TOKEN_KEY, ""+userInfo.getId());
        if (sign == null || !sign.equalsIgnoreCase(userInfo.getSign()))
            throw new BusinessException(ResponseStatus.LOGIN_OUT_OF_TIME);

        return JwtTokenUtil.generateToken(userInfo);
    }
}
