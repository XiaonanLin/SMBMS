package com.lxn.dao.bill;

import java.sql.Connection;
/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${21:30}
 * @description 订单Dao层
 */
public interface BillDao {
    //根据供应商ID查询订单数量
    int getBillCountByProviderId(Connection connection, String providerId) throws Exception;

    //增加订单
}
