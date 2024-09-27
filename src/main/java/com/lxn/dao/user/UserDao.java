package com.lxn.dao.user;

import com.lxn.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:08}
 * @description 用户Dao层
 */
public interface UserDao {
    //得到要登录的用户
    User getLoginUser(Connection connection,String userCode) throws SQLException;

    //修改当前用户密码
    int updatePwd(Connection connection,int id,String password) throws SQLException;

    //通过条件查询-用户表记录数
    int getUserCount(Connection connection, String userName, int userRole) throws SQLException;

    //根据条件查询用户列表
    List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception;

    //增加用户信息
    int add(Connection connection, User user) throws Exception;

    //通过userId删除user
    int deleteUserById(Connection connection, Integer delId) throws Exception;

    //修改用户信息
    int modify(Connection connection, User user) throws Exception;

    //通过userid获取user
    User getUserById(Connection connection,String id) throws Exception;

}
