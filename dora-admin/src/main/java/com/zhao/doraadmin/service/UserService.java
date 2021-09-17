package com.zhao.doraadmin.service;

import com.zhao.doraadmin.entity.User;
import com.zhao.dorambg.service.BaseUserService;

/**
 * @ClassName: UserService
 * @Author: zhaolianqi
 * @Date: 2021/9/17 11:48
 * @Version: v1.0
 */
public interface UserService extends BaseUserService {

    User getByName(String name);

}
