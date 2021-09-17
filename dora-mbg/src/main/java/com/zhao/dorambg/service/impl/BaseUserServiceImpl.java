package com.zhao.dorambg.service.impl;

import com.zhao.dorambg.dao.BaseUserMapper;
import com.zhao.dorambg.entity.BaseUser;
import com.zhao.dorambg.service.BaseUserService;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl extends MyBaseServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {

}
