package com.zhao.commonservice.controller;

import com.zhao.common.respvo.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AuthController
 * @Description: TODO
 * @Author: zhaolianqi
 * @Date: 2021/8/23 11:41
 * @Version: v1.0
 */
@RestController
public class AuthController {

    @GetMapping("/test")
    public BaseResponse<String> test(){
        return BaseResponse.SUCCESS("success");
    }

}
