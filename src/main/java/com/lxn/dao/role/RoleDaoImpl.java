package com.lxn.dao.role;

import com.lxn.dao.BaseDao;
import com.lxn.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.21} ${15:45}
 * @description 角色Dao层实现类
 */
public class RoleDaoImpl implements RoleDao{
    //获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Role> roleList = new ArrayList();
        if (connection!=null){
            String sql = "select * from smbms_role";
            Object []params = {};
            rs = BaseDao.execute(connection,pstm,sql,params,rs);
            while (rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("role_code"));
                role.setRoleName(rs.getString("role_name"));
                roleList.add(role);
            }
            BaseDao.closeResourse(null,pstm,rs);
        }
        return roleList;
    }
}
