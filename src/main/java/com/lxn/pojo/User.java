package com.lxn.pojo;

import java.util.Date;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${22:40}
 * @description User实体类
 */
public class User {
      private Integer id;
      private String userCode;
      private String userName;
      private String userPassword;

      private Integer gender;

      private Date birthday;
      private String phone;

      private String address;
      private  Integer userRole;
      private Integer createBy;
      private  Date createDate;

      private Integer modifyBy;

      private Date modifyDate;

      private Integer age;

      private String userRoleName;


    public User() {
    }

    public User(Integer id, String userCode, String userName, String userPassword, Integer gender, Date birthday, String phone, String address, Integer userRole, Integer createBy, Date createDate, Integer modifyBy, Date modifyDate, Integer age, String userRoleName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.age = age;
        this.userRoleName = userRoleName;
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
     * @param userCode 用户编码
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
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * 获取
     * @return gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取
     * @return userRole
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * 设置
     * @param userRole 用户角色
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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
     * @param createDate 创建日期
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
     * @param modifyBy 更新者
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
     * @param modifyDate 更新日期
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return userRoleName
     */
    public String getUserRoleName() {
        return userRoleName;
    }

    /**
     * 设置
     * @param userRoleName 用户角色名称
     */
    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Override
    public String toString() {
        return "User{id = " + id + ", userCode = " + userCode + ", userName = " + userName + ", userPassword = " + userPassword + ", gender = " + gender + ", birthday = " + birthday + ", phone = " + phone + ", address = " + address + ", userRole = " + userRole + ", createBy = " + createBy + ", createDate = " + createDate + ", modifyBy = " + modifyBy + ", modifyDate = " + modifyDate + ", age = " + age + ", userRoleName = " + userRoleName + "}";
    }
}
