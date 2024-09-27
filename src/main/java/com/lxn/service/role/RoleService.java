package com.lxn.service.role;

import com.lxn.pojo.Role;

import java.util.List;
/**
 * @author ${Linxiaonan}
 * @create ${2024.9.21} ${15:45}
 * @description 角色Service层
 */
public interface RoleService {
    //获取角色列表
    List<Role> getRoleList();
}
