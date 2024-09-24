package com.lxn.service.user;

import com.lxn.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:08}
 * @description 用户Service层
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param userCode 用户名
     * @param userPassword 用户密码
     * @return
     */
    public User login(String userCode, String userPassword);

    /**
     * 修改密码
     *
     * @param id id
     * @param pwd 密码
     * @return
     */
    //修改当前用户密码
    public Boolean updatePwd(int id, String pwd);

    //通过条件查询-用户表记录数
    public int getUserCount(String queryUserName,int queryUserRole);

    //根据条件查询用户列表
    List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    //增加用户信息
    public boolean add(User user);

    //根据userCode查询出User
    public User selectUserCodeExist(String userCode);

    //通过userId删除user
    public boolean deleteUserById(Integer delId);

    //修改用户信息

    public boolean modify(User user);

    //通过userId获取user
    public User getUserById(String id);
}
