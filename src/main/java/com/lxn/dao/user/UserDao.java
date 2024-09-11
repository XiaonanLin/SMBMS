package com.lxn.dao.user;

import com.lxn.pojo.User;

import java.sql.Connection;
/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:08}
 * @description 用户Dao层
 */
public interface UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection,String userCode,String userPassword);
}
