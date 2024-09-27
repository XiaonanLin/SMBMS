package com.lxn.service.bill;

import com.lxn.dao.BaseDao;
import com.lxn.dao.bill.BillDao;
import com.lxn.dao.bill.BillDaoImpl;
import com.lxn.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.25} ${23:27}
 * @description 订单Service层实现类
 */
public class BillServiceImpl implements BillService{
    private BillDao billDao;

    public BillServiceImpl() {
        billDao = new BillDaoImpl();
    }
    @Override
    //通过条件获取订单列表
    public List<Bill> getBillList(Bill bill) {

        Connection connection = null;
        List<Bill> billList = null;

        try {
            connection = BaseDao.getConnection();
            billList = billDao.getBillList(connection, bill);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return billList;
    }

    @Override
    //增加订单
    public boolean add(Bill bill) {

        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (billDao.add(connection, bill) > 0) {
                flag = true;
                connection.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return flag;
    }

    //通过delId删除Bill
    @Override
    public Boolean deleteBillById(String delId){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = BaseDao.getConnection();
            if (billDao.deleteBillById(connection,delId)>0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return flag;
    }

    //通过id获取订单信息
    @Override
    public Bill getBillById(String id){
        Bill bill = null;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            bill = billDao.getBillById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return bill;
    }


    @Override
    //修改订单信息
    public Boolean modify(Bill bill){
        Connection connection = null;
        Boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (billDao.modify(connection,bill)>0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResourse(connection, null, null);
        }
        return flag;
    }
}
