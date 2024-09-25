package com.lxn.dao.provider;

import com.lxn.pojo.Provider;

import java.sql.Connection;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${17:53}
 * @description 供应商Dao层
 */
public interface ProviderDao {
    //查询供应商列表
    List<Provider> getProviderList(Connection connection,String proName,String proCode)throws Exception;

    //增加供应商
    int add(Connection connection, Provider provider) throws Exception;


    //通过proId删除Provider
    int deleteProviderById(Connection connection, String delId) throws Exception;

    //修改用户信息
    int modify(Connection connection, Provider provider) throws Exception;

    //通过proId获取Provider

    Provider getProviderById(Connection connection, String id) throws Exception;
}
