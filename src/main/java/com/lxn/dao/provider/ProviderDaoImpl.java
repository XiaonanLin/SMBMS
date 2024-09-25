package com.lxn.dao.provider;

import com.lxn.dao.BaseDao;
import com.lxn.pojo.Provider;
import com.lxn.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${17:53}
 * @description 供应商Dao层实现类
 */
public class ProviderDaoImpl implements ProviderDao{
    //获取供应商列表
    @Override
    public List<Provider> getProviderList(Connection connection, String proName, String proCode) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Provider> providerList = new ArrayList<>();
        if (connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from smbms_provider where 1=1");
            List<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(proName)){
                sql.append(" and pro_name like ?");
                list.add("%" + proName + "%");
            }
            if (!StringUtils.isNullOrEmpty(proCode)) {
                sql.append(" and pro_code like ?");
                list.add("%" + proCode + "%");
            }

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection,pstm,sql.toString(),params,rs);
            while (rs.next()){
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("pro_code"));
                provider.setProName(rs.getString("pro_name"));
                provider.setProDetail(rs.getString("pro_detail"));
                provider.setProContact(rs.getString("pro_contact"));
                provider.setProPhone(rs.getString("pro_phone"));
                provider.setProAddress(rs.getString("pro_address"));
                provider.setProFax(rs.getString("pro_fax"));
                provider.setCreateDate(rs.getTimestamp("create_date"));
                providerList.add(provider);
            }

            BaseDao.closeResourse(null,pstm,rs);
        }

        return providerList;
    }

    //增加供应商
    @Override
    public int add(Connection connection, Provider provider) throws Exception{
        PreparedStatement pstm = null;
        int flag = 0;
        if (null!=connection){
            String sql = "insert into smbms_provider (pro_code,pro_name,pro_detail,pro_contact,pro_phone,pro_address,pro_fax,create_by,create_date) values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {provider.getProCode(), provider.getProName(), provider.getProDetail(),
                    provider.getProContact(), provider.getProPhone(), provider.getProAddress(),
                    provider.getProFax(), provider.getCreateBy(), provider.getCreateDate()};
            flag = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return flag;
    }

    //通过proId删除Provider
    @Override
    public int deleteProviderById(Connection connection, String delId) throws Exception{
        PreparedStatement pstm = null;
        int flag = 0;
        if (null!=connection){
            String sql = "delete from smbms_provider where id =?";
            Object[] params = {delId};
            flag = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return flag;
    }

    //修改供应商信息
    @Override
    public int modify(Connection connection, Provider provider) throws Exception{
        PreparedStatement pstm = null;
        int updateRows = 0;
        if (null!=connection){
            String sql = "update smbms_provider set pro_name = ?,pro_detail =?,pro_contact = ?,pro_phone = ?,pro_address = ?,pro_fax = ?,modify_by = ?,modify_date = ? where id =?";
            Object[] params = {provider.getProName(), provider.getProDetail(), provider.getProContact(), provider.getProPhone(), provider.getProAddress(),
                    provider.getProFax(), provider.getModifyBy(), provider.getModifyDate(), provider.getId()};
            updateRows = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return updateRows;
    }

    //通过proId获取Provider
    @Override
    public Provider getProviderById(Connection connection, String id) throws Exception{
        PreparedStatement pstm = null;
        Provider provider = null;
        ResultSet rs = null;
        if (null!=connection){
            String sql = "select * from smbms_provider where id = ?";
            Object[] params = {id};
            rs = BaseDao.execute(connection,pstm,sql,params,rs);
            if (rs.next()){
                provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setProCode(rs.getString("pro_code"));
                provider.setProName(rs.getString("pro_name"));
                provider.setProDetail(rs.getString("pro_detail"));
                provider.setProContact(rs.getString("pro_contact"));
                provider.setProPhone(rs.getString("pro_phone"));
                provider.setProAddress(rs.getString("pro_address"));
                provider.setProFax(rs.getString("pro_fax"));
                provider.setCreateBy(rs.getInt("create_By"));
                provider.setCreateDate(rs.getTimestamp("create_date"));
                provider.setModifyBy(rs.getInt("modify_by"));
                provider.setModifyDate(rs.getTimestamp("modify_date"));
            }
            BaseDao.closeResourse(null,pstm,rs);
        }
        return provider;
    }
}
