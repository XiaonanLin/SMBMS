package com.lxn.service.bill;

import com.lxn.pojo.Bill;

import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.25} ${23:27}
 * @description 订单Service层
 */
public interface BillService {
    //通过条件获取订单列表
    List<Bill> getBillList(Bill bill);

    //增加订单
    boolean add(Bill bill);

    //通过delId删除Bill
    Boolean deleteBillById(String delId);

    //通过id获取订单信息
    Bill getBillById(String id);

    //修改订单信息
    Boolean modify(Bill bill);
}
