package com.lxn.pojo;

import java.util.Date;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${23:17}
 * @description User实体类
 */
public class Role {
    private Integer id;
    private String userCode;
    private String userName;

    private Integer createBy;
    private Date createDate;

    private Integer modifyBy;

    private Date modifyDate;

    private Integer userId;

    private String providerName;
    public Role() {
    }

    public Role(Integer id, String userCode, String userName, Integer createBy, Date createDate, Integer modifyBy, Date modifyDate, Integer userId) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
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
     * @return userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置
     * @param userCode 角色编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
        return "Role{id = " + id + ", userCode = " + userCode + ", userName = " + userName + ", createBy = " + createBy + ", createDate = " + createDate + ", modifyBy = " + modifyBy + ", modifyDate = " + modifyDate + ", userId = " + userId + "}";
    }
}
