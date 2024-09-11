package com.lxn.pojo;

import java.util.Date;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${22:56}
 * @description Address实体类
 */
public class Address {
    private Integer id;
    private String contact;
    private String addressDetail;
    private String postcode;

    private String tel;
    private Integer createBy;
    private Date createDate;

    private Integer modifyBy;

    private Date modifyDate;

    private Integer userId;


    public Address() {
    }

    public Address(Integer id, String contact, String addressDetail, String postcode, String tel, Integer createBy, Date createDate, Integer modifyBy, Date modifyDate, Integer userId) {
        this.id = id;
        this.contact = contact;
        this.addressDetail = addressDetail;
        this.postcode = postcode;
        this.tel = tel;
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
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置
     * @param contact 联系人名字
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取
     * @return addressDetail
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * 设置
     * @param addressDetail 收货地址明细
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    /**
     * 获取
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置
     * @param postcode 邮编
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * 获取
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置
     * @param tel 联系人电话
     */
    public void setTel(String tel) {
        this.tel = tel;
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
        return "Address{id = " + id + ", contact = " + contact + ", addressDetail = " + addressDetail + ", postcode = " + postcode + ", tel = " + tel + ", createBy = " + createBy + ", createDate = " + createDate + ", modifyBy = " + modifyBy + ", modifyDate = " + modifyDate + ", userId = " + userId + "}";
    }
}
