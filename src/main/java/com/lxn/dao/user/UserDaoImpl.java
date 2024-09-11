package com.lxn.dao.user;

import com.lxn.dao.BaseDao;
import com.lxn.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:08}
 * @description 用户Dao层实现类
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String userCode,String userPassword) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        try{
            if (null!=connection){
                //查询编码和密码相同的用户
                String sql = "select * from smbms_user where user_code = ? and user_password = ?";
                Object[] params = {userCode,userPassword};
                rs = BaseDao.execute(connection,sql,params,rs,pstm);

                //如果用户存在，new一个user对象保存用户信息
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("user_code"));
                    user.setUserName(rs.getString("user_name"));
                    user.setUserPassword(rs.getString("user_password"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("user_role"));
                    user.setCreateBy(rs.getInt("create_by"));
                    user.setCreateDate(rs.getTimestamp("create_date"));
                    user.setModifyBy(rs.getInt("modify_by"));
                    user.setModifyDate(rs.getTimestamp("modify_date"));
                }
            }
            //不关connection,connection在业务层有其他操作
            BaseDao.closeResourse(null,pstm,rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
