package com.zhao.doraadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhao.common.modal.UserInfo;
import com.zhao.common.respvo.BaseResponse;
import com.zhao.commonservice.annotation.CurrentUser;
import com.zhao.commonservice.annotation.LoginRequired;
import com.zhao.doraadmin.service.RoleService;
import com.zhao.dorambg.entity.Role;
import com.zhao.dorambg.reqvo.BaseReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RoleApi
 * @Author: zhaolianqi
 * @Date: 2021/9/24 11:01
 * @Version: v1.0
 */
@LoginRequired
@RestController
@RequestMapping("/api/role")
public class RoleApi {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public BaseResponse<IPage<Role>> getPage(BaseReqVO reqVO){
        return BaseResponse.SUCCESS(roleService.getPage(reqVO));
    }

    @PostMapping
    public BaseResponse<Boolean> saveOrUpdate(@RequestBody Role entity, @CurrentUser UserInfo user){
        if (entity.getId() == null){
            entity.setCreatorId(user.getId());
        }
        return BaseResponse.SUCCESS(roleService.saveOrUpdate(entity));
    }

}
