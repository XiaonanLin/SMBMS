package com.lxn.service.user;

import com.lxn.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

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
}
