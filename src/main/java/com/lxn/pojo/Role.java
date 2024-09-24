package com.lxn.pojo;

import java.util.Date;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${23:17}
 * @description User实体类
 */
public class Role {
    private Integer id;
    private String roleCode;
    private String roleName;

    private Integer createBy;
    private Date createDate;

    private Integer modifyBy;

    private Date modifyDate;

    private Integer userId;

    private String providerName;
    public Role() {
    }

    public Role(Integer id, String roleCode, String roleName, Integer createBy, Date createDate, Integer modifyBy, Date modifyDate, Integer userId) {
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.userId = userId;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return roleCode
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置
     * @param roleCode 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取
     * @return roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取
     * @return createBy
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * 设置
     * @param createBy 创建者
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取
     * @return createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取
     * @return modifyBy
     */
    public Integer getModifyBy() {
        return modifyBy;
    }

    /**
     * 设置
     * @param modifyBy 修改者
     */
    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    /**
     * 获取
     * @return modifyDate
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Role{id = " + id + ", roleCode = " + roleCode + ", roleName = " + roleName + ", createBy = " + createBy + ", createDate = " + createDate + ", modifyBy = " + modifyBy + ", modifyDate = " + modifyDate + ", userId = " + userId + "}";
    }
}
