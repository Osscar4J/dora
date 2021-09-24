package com.zhao.doraadmin.service;

import com.zhao.dorambg.service.BaseRoleService;

/**
 * @ClassName: RoleService
 * @Author: zhaolianqi
 * @Date: 2021/9/24 10:30
 * @Version: v1.0
 */
public interface RoleService extends BaseRoleService {

    /**
     * 角色类型
     * @Author zhaolianqi
     * @Date 2021/9/24 10:42
     */
    interface Type {
        /** 系统默认 */
        int DEFAULT = 0;
        /** 用户自定义 */
        int CUSTOM = 1;
    }

}
