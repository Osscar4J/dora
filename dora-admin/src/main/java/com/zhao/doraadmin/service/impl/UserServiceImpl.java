package com.zhao.doraadmin.service.impl;

import com.zhao.commonservice.utils.Asserts;
import com.zhao.doraadmin.dao.UserMapper;
import com.zhao.doraadmin.entity.User;
import com.zhao.doraadmin.service.UserService;
import com.zhao.dorambg.service.impl.BaseUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Author: zhaolianqi
 * @Date: 2021/9/17 11:48
 * @Version: v1.0
 */
@Service
public class UserServiceImpl extends BaseUserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByName(String name) {
        Asserts.notEmpty(name);
        return userMapper.selectByName(name);
    }
}
