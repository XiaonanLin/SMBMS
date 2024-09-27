package com.lxn.dao.bill;

import com.lxn.dao.BaseDao;
import com.lxn.pojo.Bill;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${21:30}
 * @description 订单Dao层实现类
 */
public class BillDaoImpl implements BillDao{
    //根据供应商ID查询订单数量
    @Override
    public int getBillCountByProviderId(Connection connection, String providerId) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (null!=connection){
            String sql = "select count(1) as billCount from smbms_bill where provider_id = ?";
            Object[] params = {providerId};
            rs = BaseDao.execute(connection,pstm,sql,params,rs);
            if (rs.next()){
                count = rs.getInt("billCount");
            }
            BaseDao.closeResourse(null,pstm,rs);
        }

        return count;
    }

    //通过查询条件获取供应商列表
    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select b.*,p.pro_name as providerName from smbms_bill b, smbms_provider p where b.provider_id = p.id");
            List<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(bill.getProductName())) {
                sql.append(" and product_name like ?");
                list.add("%" + bill.getProductName() + "%");
            }
            if (bill.getProviderId() > 0) {
                sql.append(" and provider_id = ?");
                list.add(bill.getProviderId());
            }
            if (bill.getIsPayment() > 0) {
                sql.append(" and is_payment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection,pstm,sql.toString(),params,rs);
            while (rs.next()) {
                Bill bill1 = new Bill();
                bill1.setId(rs.getInt("id"));
                bill1.setBillCode(rs.getString("bill_code"));
                bill1.setProductName(rs.getString("product_name"));
                bill1.setProductDetail(rs.getString("product_detail"));
                bill1.setProductUnit(rs.getString("product_unit"));
                bill1.setProductCount(rs.getBigDecimal("product_count"));
                bill1.setTotalPrice(rs.getBigDecimal("total_price"));
                bill1.setIsPayment(rs.getInt("is_payment"));
                bill1.setProviderId(rs.getInt("provider_id"));
                bill1.setProviderName(rs.getString("providerName"));
                bill1.setCreateDate(rs.getTimestamp("create_date"));
                bill1.setCreateBy(rs.getInt("create_by"));
                billList.add(bill1);
            }
            BaseDao.closeResourse(null, pstm, rs);
        }
        return billList;
    }

    @Override
    //增加订单
    public int add(Connection connection, Bill bill) throws Exception {
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "insert into smbms_bill (bill_code,product_name,product_detail,product_unit,product_count,total_price,is_payment,provider_id,create_by,create_date) values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {bill.getBillCode(), bill.getProductName(), bill.getProductDetail(),
                    bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(),
                    bill.getProviderId(), bill.getCreateBy(), bill.getCreateDate()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResourse(null, pstm, null);
        }
        return flag;
    }

    @Override
    //通过delId删除Bill
    public int deleteBillById(Connection connection, String delId){
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "delete from smbms_bill where id = ?";
            Object[] params = {delId};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResourse(null, pstm, null);
        }
        return flag;
    }

    @Override
    //通过id获取订单信息
    public Bill getBillById(Connection connection,String id) throws Exception{
        PreparedStatement pstm = null;
        Bill bill = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select b.*,p.pro_name as providerName from smbms_bill b, smbms_provider p where b.provider_id = p.id and b.id = ?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, pstm, sql, params,rs);
            if (rs.next()){
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("bill_code"));
                bill.setProductName(rs.getString("product_name"));
                bill.setProductDetail(rs.getString("product_detail"));
                bill.setProductUnit(rs.getString("product_unit"));
                bill.setProductCount(rs.getBigDecimal("product_count"));
                bill.setTotalPrice(rs.getBigDecimal("total_price"));
                bill.setIsPayment(rs.getInt("is_payment"));
                bill.setProviderId(rs.getInt("provider_id"));
                bill.setProviderName(rs.getString("providerName"));
                bill.setModifyBy(rs.getInt("modify_by"));
                bill.setModifyDate(rs.getTimestamp("modify_date"));
            }
            BaseDao.closeResourse(null, pstm, null);
        }
        return bill;
    }


    @Override
    //修改订单信息
    public int modify(Connection connection, Bill bill) throws Exception{
        PreparedStatement pstm = null;
        int flag = 0;
        if (null!=connection){
            String sql = "update smbms_bill set product_name=?,product_unit=?,product_count=?,total_price=?,is_payment=?,provider_id=?,modify_by=?,modify_date=? where id = ? ";
            Object[] params = {bill.getProductName(),
                    bill.getProductUnit(), bill.getProductCount(), bill.getTotalPrice(), bill.getIsPayment(),
                    bill.getProviderId(), bill.getModifyBy(), bill.getModifyDate(), bill.getId()};
            flag= BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return flag;
    }
}
