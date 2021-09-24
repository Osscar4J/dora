package com.zhao.dorambg.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: Role
 * @Author: zhaolianqi
 * @Date: 2021/9/24 10:28
 * @Version: v1.0
 */
@TableName("role_tb")
public class Role extends BaseEntity {

    private Integer type;
    private Long parentId;
    private Long creatorId;
    private String name;
    private String description;
    private String lvChain;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLvChain() {
        return lvChain;
    }

    public void setLvChain(String lvChain) {
        this.lvChain = lvChain;
    }
}
