package com.zhao.doraadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhao.common.respvo.BaseResponse;
import com.zhao.commonservice.annotation.CommonPath;
import com.zhao.commonservice.annotation.LoginRequired;
import com.zhao.doraadmin.service.UserService;
import com.zhao.dorambg.entity.User;
import com.zhao.dorambg.reqvo.BaseReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserApi
 * @Author: zhaolianqi
 * @Date: 2021/8/24 16:33
 * @Version: v1.0
 */
@LoginRequired
@RestController
@RequestMapping("/api/user")
public class UserApi {

    @Autowired
    private UserService userService;

    @CommonPath
    @GetMapping
    public BaseResponse<IPage<User>> getUser(BaseReqVO reqVO){
        return BaseResponse.SUCCESS(userService.getPage(reqVO));
    }

    @CommonPath
    @GetMapping("/name/{name}")
    public BaseResponse<User> getByName(@PathVariable String name){
        return BaseResponse.SUCCESS(userService.getByName(name));
    }

}
