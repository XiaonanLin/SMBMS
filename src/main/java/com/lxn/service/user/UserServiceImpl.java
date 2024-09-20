package com.lxn.service.user;

import com.lxn.dao.BaseDao;
import com.lxn.dao.user.UserDao;
import com.lxn.dao.user.UserDaoImpl;
import com.lxn.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:26}
 * @description 用户Service层实现类
 */
public class UserServiceImpl implements UserService {
    //业务层调用Dao层，所以我们要引入Dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    /**
     * 用户登录实现
     *
     * @param userCode 用户名
     * @param userPassword 用户密码
     * @return
     */
    @Override
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try{
            //获取连接
            connection = BaseDao.getConnection();
            //通过业务层调用对应的具体数据库操作，得到user对象
            user = userDao.getLoginUser(connection,userCode,userPassword);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }

        return user;


    }

    /**
     * 修改密码实现
     *
     * @param id id
     * @param pwd 密码
     * @return
     */
    @Override
    public Boolean updatePwd(int id, String pwd){
        Connection connection = null;
        boolean flag = false;
        //修改密码
        try{
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,pwd)>0){
                flag = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }
}
