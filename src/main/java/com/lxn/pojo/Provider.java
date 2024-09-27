package com.lxn.pojo;

import java.util.Date;
/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${23:17}
 * @description Provider实体类
 */
public class Provider {
    private Integer id;
    private String proCode;
    private String proName;
    private String proDetail;

    private String proContact;
    private String proPhone;

    private String proAddress;

    private String proFax;

    private Integer createBy;
    private Date createDate;

    private Integer modifyBy;

    private Date modifyDate;


    public Provider() {
    }

    public Provider(Integer id, String proCode, String proName, String proDetail, String proContact, String proPhone, String proAddress, String proFax, Integer createBy, Date createDate, Integer modifyBy, Date modifyDate) {
        this.id = id;
        this.proCode = proCode;
        this.proName = proName;
        this.proDetail = proDetail;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
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
     * @return proCode
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * 设置
     * @param proCode 供应商编码
     */
    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    /**
     * 获取
     * @return proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * 设置
     * @param proName 供应商名称
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * 获取
     * @return proDetail
     */
    public String getProDetail() {
        return proDetail;
    }

    /**
     * 设置
     * @param proDetail 供应商描述
     */
    public void setProDetail(String proDetail) {
        this.proDetail = proDetail;
    }

    /**
     * 获取
     * @return proContact
     */
    public String getProContact() {
        return proContact;
    }

    /**
     * 设置
     * @param proContact 供应商联系人
     */
    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    /**
     * 获取
     * @return proPhone
     */
    public String getProPhone() {
        return proPhone;
    }

    /**
     * 设置
     * @param proPhone 供应商电话
     */
    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    /**
     * 获取
     * @return proAddress
     */
    public String getProAddress() {
        return proAddress;
    }

    /**
     * 设置
     * @param proAddress 供应商地址
     */
    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    /**
     * 获取
     * @return proFax
     */
    public String getProFax() {
        return proFax;
    }

    /**
     * 设置
     * @param proFax 供应商传真
     */
    public void setProFax(String proFax) {
        this.proFax = proFax;
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
     * @param modifyDate 更新时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Provider{id = " + id + ", proCode = " + proCode + ", proName = " + proName + ", proDetail = " + proDetail + ", proContact = " + proContact + ", proPhone = " + proPhone + ", proAddress = " + proAddress + ", proFax = " + proFax + ", createBy = " + createBy + ", createDate = " + createDate + ", modifyBy = " + modifyBy + ", modifyDate = " + modifyDate + "}";
    }
}
