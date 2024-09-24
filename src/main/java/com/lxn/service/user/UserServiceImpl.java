package com.lxn.service.user;

import com.lxn.dao.BaseDao;
import com.lxn.dao.user.UserDao;
import com.lxn.dao.user.UserDaoImpl;
import com.lxn.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
            user = userDao.getLoginUser(connection,userCode);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        if (null != user){
            //密码不正确
            if (!user.getUserPassword().equals(userPassword)){
                user = null;
            }
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

    @Override
    public int getUserCount(String queryUserName,int queryUserRole){
        Connection connection = null;
        int count = 0;
        try{
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection,queryUserName,queryUserRole);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return count;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize){
        Connection connection = null;
        List<User> userList = null;
        try{
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection,queryUserName,queryUserRole,currentPageNo,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return userList;
    }

    @Override
    //增加用户信息
    public boolean add(User user){
        Boolean flag = false;
        Connection connection = null;

        try{
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);
            int updateRows = userDao.add(connection,user);
            connection.commit();
            if(updateRows > 0){
                flag = true;
                System.out.println("add success!");
            }else {
                System.out.println("add failed!");
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }

        return flag;
    }

    @Override
    public User selectUserCodeExist(String userCode) {
        Connection connection = null;
        User user = null;
        try{
            connection = BaseDao.getConnection();
            user = userDao.getLoginUser(connection,userCode);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return user;
    }

    //通过userId删除user
    @Override
    public boolean deleteUserById(Integer delId){
        Connection connection = null;
        boolean flag = false;
        try{
            connection = BaseDao.getConnection();
            if (userDao.deleteUserById(connection,delId)>0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }

    //修改用户信息
    @Override

    public boolean modify(User user){
        Connection connection = null;
        boolean flag = false;
        try{
            connection = BaseDao.getConnection();
            if (userDao.modify(connection,user)>0){
                flag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }
        return flag;
    }

    //通过userId获取user
    @Override
    public User getUserById(String id) {
        Connection connection = null;
        User user = null;
        try{
            connection = BaseDao.getConnection();
            user = userDao.getUserById(connection,id);
        }catch (Exception e){
            e.printStackTrace();
            user = null;
        }finally {
            BaseDao.closeResourse(connection,null,null);
        }

        return user;
    }
}
