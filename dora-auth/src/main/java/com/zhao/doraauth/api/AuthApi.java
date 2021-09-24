package com.zhao.doraauth.api;

import com.zhao.common.respvo.BaseResponse;
import com.zhao.common.utils.JwtTokenUtil;
import com.zhao.common.utils.TokenConfig;
import com.zhao.doraauth.entity.Account;
import com.zhao.doraauth.resp.LoginRespVO;
import com.zhao.doraauth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: AuthApi
 * @Author: zhaolianqi
 * @Date: 2021/9/23 14:30
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public BaseResponse<LoginRespVO> login(@RequestBody Account entity){
        return BaseResponse.SUCCESS(accountService.login(entity));
    }

    @GetMapping("/refreshToken")
    public BaseResponse<String> refreshToken(@RequestHeader("refresh") String refresh){
        return BaseResponse.SUCCESS(accountService.getAccessTokenByRefreshToken(refresh));
    }

    @GetMapping("/tokenConfig-internal")
    public BaseResponse<TokenConfig> getTokenConfig(){
        return BaseResponse.SUCCESS(JwtTokenUtil.getConfiguration());
    }

}
