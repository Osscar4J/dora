package com.zhao.dorambg.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName: Menu
 * @Author: zhaolianqi
 * @Date: 2021/9/24 10:57
 * @Version: v1.0
 */
@TableName("menu_tb")
public class Menu extends BaseEntity {

    private Integer type;
    private Integer sortNo;
    private Integer target;
    private Long parentId;
    private Long creatorId;
    private String name;
    private String link;
    private String icon;
    private String description;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
