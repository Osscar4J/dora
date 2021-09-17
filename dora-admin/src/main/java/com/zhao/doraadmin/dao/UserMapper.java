package com.zhao.doraadmin.dao;

import com.zhao.dorambg.dao.BaseUserMapper;
import com.zhao.dorambg.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserMapper
 * @Author: zhaolianqi
 * @Date: 2021/9/17 10:59
 * @Version: v1.0
 */
@Repository
public interface UserMapper extends BaseUserMapper {

    User selectByName(@Param("name") String name);

}
