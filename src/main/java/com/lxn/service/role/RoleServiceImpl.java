package com.lxn.service.role;


import com.lxn.dao.BaseDao;
import com.lxn.dao.role.RoleDao;
import com.lxn.dao.role.RoleDaoImpl;
import com.lxn.pojo.Role;

import java.sql.Connection;
import java.util.List;

public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }


    //获取角色列表
    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try{
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return roleList;
    }
}
