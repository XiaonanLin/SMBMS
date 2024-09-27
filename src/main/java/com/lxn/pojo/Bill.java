package com.lxn.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${22:55}
 * @description Bill实体类
 */
public class Bill {
    private Integer id;
    private String billCode;
    private String productName;
    private String productDetail;

    private String productUnit;
    private BigDecimal productCount;

    private BigDecimal totalPrice;

    private Integer isPayment;

    private Integer createBy;
    private  Date createDate;

    private Integer modifyBy;

    private Date modifyDate;

    private Integer providerId;

    private String providerName;


    public Bill() {
    }

    public Bill(Integer id, String billCode, String productName, String productDetail, String productUnit, BigDecimal productCount, BigDecimal totalPrice, Integer isPayment, Integer createBy, Date createDate, Integer modifyBy, Date modifyDate, Integer providerId, String providerName) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productDetail = productDetail;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.providerId = providerId;
        this.providerName = providerName;
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
     * @return billCode
     */
    public String getBillCode() {
        return billCode;
    }

    /**
     * 设置
     * @param billCode 账单编码
     */
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    /**
     * 获取
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取
     * @return productDetail
     */
    public String getProductDetail() {
        return productDetail;
    }

    /**
     * 设置
     * @param productDetail 商品描述
     */
    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    /**
     * 获取
     * @return productUnit
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * 设置
     * @param productUnit 商品单位
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * 获取
     * @return productCount
     */
    public BigDecimal getProductCount() {
        return productCount;
    }

    /**
     * 设置
     * @param productCount 商品数量
     */
    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    /**
     * 获取
     * @return totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置
     * @param totalPrice 商品总额
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取
     * @return isPayment
     */
    public Integer getIsPayment() {
        return isPayment;
    }

    /**
     * 设置
     * @param isPayment 是否支付（1：未支付 2：已支付）
     */
    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
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
     * @param createBy 创建者（user_id）
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
     * @param modifyBy 更新者（user_id）
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

    /**
     * 获取
     * @return providerId
     */
    public Integer getProviderId() {
        return providerId;
    }

    /**
     * 设置
     * @param providerId 用户id
     */
    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    /**
     * 获取
     * @return providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * 设置
     * @param providerName 供应商名称
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String toString() {
        return "Bill{id = " + id + ", billCode = " + billCode + ", productName = " + productName + ", productDetail = " + productDetail + ", productUnit = " + productUnit + ", productCount = " + productCount + ", totalPrice = " + totalPrice + ", isPayment = " + isPayment + ", createBy = " + createBy + ", createDate = " + createDate + ", modifyBy = " + modifyBy + ", modifyDate = " + modifyDate + ", providerId = " + providerId + ", providerName = " + providerName + "}";
    }
}
