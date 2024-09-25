package com.lxn.dao.bill;

import com.lxn.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillDaoImpl implements BillDao{
    //根据供应商ID查询订单数量
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
}
