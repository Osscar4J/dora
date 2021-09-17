package com.zhao.dorambg.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: User
 * @Author: zhaolianqi
 * @Date: 2021/9/17 10:10
 * @Version: v1.0
 */
@TableName("user_tb")
public class User extends BaseEntity {

    private Integer gender;
    private String name;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
