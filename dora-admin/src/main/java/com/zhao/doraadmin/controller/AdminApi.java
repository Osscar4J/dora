package com.zhao.doraadmin.controller;

import com.zhao.common.respvo.BaseResponse;
import com.zhao.commonservice.annotation.CommonPath;
import com.zhao.commonservice.annotation.LoginRequired;
import com.zhao.commonservice.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: AdminApi
 * @Author: zhaolianqi
 * @Date: 2021/8/24 16:33
 * @Version: v1.0
 */
@LoginRequired
@RestController
@RequestMapping("/api/admin")
public class AdminApi {

    private Logger logger = LoggerFactory.getLogger(AdminApi.class);

    @GetMapping
    public BaseResponse<String> test(){
        return BaseResponse.SUCCESS();
    }

    @CommonPath
    @GetMapping("/ip")
    public BaseResponse<String> getIp(HttpServletRequest request){
        return BaseResponse.SUCCESS(CommonUtils.getIpAddr(request) + " --1");
    }

}
