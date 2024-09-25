package com.lxn.service.provider;

import com.lxn.pojo.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${17:53}
 * @description 供应商Service层
 */
public interface ProviderService {

    //获取供应商列表
    public List<Provider> getProviderList(String proName, String proCode);

    //增加供应商
    public boolean add(Provider provider);

    //通过proId删除Provider
    public int deleteProviderById(String delId) throws Exception;

    //修改用户信息
    public boolean modify(Provider provider) throws Exception;

    //通过proId获取Provider

    public Provider getProviderById(String id) throws Exception;
}
