package com.zhao.doraadmin.service.impl;

import com.zhao.commonservice.utils.Asserts;
import com.zhao.doraadmin.service.RoleService;
import com.zhao.dorambg.entity.Role;
import com.zhao.dorambg.service.impl.BaseRoleServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RoleServiceImpl
 * @Author: zhaolianqi
 * @Date: 2021/9/24 10:31
 * @Version: v1.0
 */
@Service
public class RoleServiceImpl extends BaseRoleServiceImpl implements RoleService {

    @Override
    public boolean save(Role entity) {
        Asserts.notEmpty(entity.getName());
        return super.save(entity);
    }
}
