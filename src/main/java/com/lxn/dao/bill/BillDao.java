package com.lxn.dao.bill;

import com.lxn.pojo.Bill;

import java.sql.Connection;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${21:30}
 * @description 订单Dao层
 */
public interface BillDao {
    //根据供应商ID查询订单数量
    int getBillCountByProviderId(Connection connection, String providerId) throws Exception;

    //增加订单
    int add(Connection connection, Bill bill) throws Exception;

    //通过查询条件获取供应商列表
    List<Bill> getBillList(Connection connection, Bill bill) throws Exception;

    //通过delId删除Bill
    int deleteBillById(Connection connection, String delId) throws Exception;

    //通过id获取订单信息
    Bill getBillById(Connection connection,String id) throws Exception;

    //修改订单信息
    int modify(Connection connection, Bill bill) throws Exception;
}
