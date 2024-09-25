package com.lxn.service.provider;

import com.lxn.dao.BaseDao;
import com.lxn.dao.bill.BillDao;
import com.lxn.dao.bill.BillDaoImpl;
import com.lxn.dao.provider.ProviderDao;
import com.lxn.dao.provider.ProviderDaoImpl;
import com.lxn.pojo.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${17:53}
 * @description 供应商Service层实现类
 */
public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao;
    private BillDao billDao;

    public ProviderServiceImpl() {
        providerDao = new ProviderDaoImpl();
        billDao = new BillDaoImpl();
    }

    //获取供应商列表
    @Override
    public List<Provider> getProviderList(String proName, String proCode){
        Connection connection = null;
        List<Provider> providerList = null;
        try{
            connection = BaseDao.getConnection();
            providerList = providerDao.getProviderList(connection,proName,proCode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return providerList;
    }

    //增加供应商

    @Override
    public boolean add(Provider provider) {
        Connection connection = null;
        Boolean flag = false;
        try{
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            if (providerDao.add(connection,provider)>0){
                flag = true;
            }
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            try{
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }

    //通过proId删除Provider
    public int deleteProviderById(String delId){
        Connection connection = null;
        int billcount = -1;
        try{
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            billcount = billDao.getBillCountByProviderId(connection,delId);
            if (billcount == 0){
                providerDao.deleteProviderById(connection, delId);
            }
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            billcount = -1;
            try{
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return billcount;
    }

    //修改用户信息

    @Override
    public boolean modify(Provider provider) throws Exception {
        Connection connection = null;
        boolean flag = false;

        try{
            connection = BaseDao.getConnection();
            if (providerDao.modify(connection,provider)>0){
                flag  = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }

        return flag;
    }

    //通过proId获取Provider
    @Override
    public Provider getProviderById(String id) throws Exception{
        Connection connection = null;
        Provider provider = null;
        try{
            connection = BaseDao.getConnection();
            provider = providerDao.getProviderById(connection,id);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return provider;
    }
}
