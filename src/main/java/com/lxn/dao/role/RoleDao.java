package com.lxn.dao.role;

import com.lxn.pojo.Role;


import java.sql.Connection;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.21} ${15:45}
 * @description 角色Dao层
 */
public interface RoleDao {
    //获取角色列表
    List<Role> getRoleList(Connection connection) throws Exception;
}
