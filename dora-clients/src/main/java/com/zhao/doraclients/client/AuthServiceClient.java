package com.zhao.doraclients.client;

import com.zhao.common.respvo.BaseResponse;
import com.zhao.common.utils.TokenConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: AuthServiceClient
 * @Author: zhaolianqi
 * @Date: 2021/8/26 14:43
 * @Version: v1.0
 */
@FeignClient(name = "dora-auth")
public interface AuthServiceClient {

    @GetMapping("/api/auth/tokenConfig-internal")
    BaseResponse<TokenConfig> getTokenConfig();

}
