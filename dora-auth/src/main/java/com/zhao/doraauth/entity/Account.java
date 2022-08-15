package com.zhao.doraauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhao.common.modal.UserInfo;
import com.zhao.dorambg.entity.SuperEntity;

import java.util.Date;

/**
 * @ClassName: Account
 * @Author: zhaolianqi
 * @Date: 2021/9/23 14:05
 * @Version: v1.0
 */
@TableName("account_tb")
public class Account extends SuperEntity implements UserInfo {

    @TableId(type = IdType.INPUT)
    private String account;
    private Long userId;
    private Integer isDel;
    private Integer status;
    private Integer type;
    private Date createTime;
    private Date updateTime;
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public Long getId() {
        return this.userId;
    }

    @Override
    public String getSign() {
        return null;
    }
}
