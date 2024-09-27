package com.lxn.service.provider;

import com.lxn.pojo.Provider;

import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${17:53}
 * @description 供应商Service层
 */
public interface ProviderService {

    //获取供应商列表
    List<Provider> getProviderList(String proName, String proCode);

    //增加供应商
    boolean add(Provider provider);

    //通过proId删除Provider
    int deleteProviderById(String delId) throws Exception;

    //修改用户信息
    boolean modify(Provider provider) throws Exception;

    //通过proId获取Provider

    Provider getProviderById(String id) throws Exception;
}
