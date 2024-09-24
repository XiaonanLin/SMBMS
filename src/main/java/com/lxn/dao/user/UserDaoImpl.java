package com.lxn.dao.user;

import com.lxn.dao.BaseDao;
import com.lxn.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:08}
 * @description 用户Dao层实现类
 */
public class UserDaoImpl implements UserDao{
    //得到要登录的用户
    @Override
    public User getLoginUser(Connection connection, String userCode)throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        try{
            if (null!=connection){
                //查询编码和密码相同的用户
                String sql = "select * from smbms_user where user_code = ?";
                Object[] params = {userCode};
                rs = BaseDao.execute(connection,pstm,sql,params,rs);

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

    //修改当前用户密码
    @Override
    public int updatePwd(Connection connection,int id,String password) throws SQLException{
        PreparedStatement pstm = null;
        int execute = 0;
        if (connection!=null) {
            String sql = "update smbms_user set user_password = ? where id = ?";
            Object params[] = {password, id};
            //返回修改行数 不等于0即为修改成功
            execute = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResourse(null,pstm,null);
        }

        return execute;
    }

    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;
        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            //聚合函数 是一种特殊的 SQL 函数，它将对一组行的数据执行某种操作，然后返回单一结果。
            //SQL聚合函数count(1) 意思是“对所有符合条件的行进行计数”，在这里，1 只是一个占位符；
            //通过 AS count 给聚合函数的结果起了个别名，数据库会在 ResultSet 中用这个别名作为列名
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.user_role = r.id");
            //List用来储存参数
            List<Object> list = new ArrayList();
            if (!StringUtils.isNullOrEmpty(userName)){
                //模糊查询 要实现模糊查询需要加%
                sql.append(" and u.user_name like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0){
                sql.append(" and u.user_role = ?");
                list.add(userRole);
            }

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection,pstm,sql.toString(),params,rs);
            if (rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResourse(null,pstm,rs);
        }
        return count;
    }

    //根据条件查询用户列表
    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception{
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            //返回 smbms_user 表的所有字段，再加上 smbms_role 表中的 roleName 字段，并将其命名为 userRoleName
            sql.append("select u.*,r.role_name as user_role_name from smbms_user u,smbms_role r where u.user_role = r.id");
            List<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.user_name like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0){
                sql.append(" and u.user_role = ?");
                list.add(userRole);
            }
            /*
              分页查询 - limit m,n
              m：起始索引，表示从第几条索引开始，计算方式（当前页-1）*每页显示条数
              n：每页显示条数
            */
            sql.append(" order by create_date DESC limit ?,?");
            //输入的是 当前页的页码,用页码计算出 分页查询的起始索引，并赋给同一个变量，查询的时候currentPageNo 不再表示页码，而是作为分页的起始索引传递给 SQL 查询
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection,pstm,sql.toString(),params,rs);
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("user_code"));
                user.setUserName(rs.getString("user_name"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("user_role"));
                user.setUserRoleName(rs.getString("user_role_name"));
                userList.add(user);
            }
            BaseDao.closeResourse(null,pstm,rs);
        }
        return userList;
    }

    //增加用户信息
    @Override
    public int add(Connection connection, User user){
        PreparedStatement pstm = null;
        int updateRows = 0;
        if (null != connection){
            String sql = "insert into smbms_user (user_code,user_name,user_password,user_role,gender,birthday,phone,address,create_date,create_by)"+
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(), user.getUserRole(),
            user.getGender(),user.getBirthday(),user.getPhone(),user.getAddress(),user.getCreateDate(),user.getCreateBy()};
            updateRows = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return updateRows;
    }

    //通过userId删除user
    @Override
    public int deleteUserById(Connection connection, Integer delId) throws Exception{
        PreparedStatement pstm = null;
        int flag = 0;
        if (null!=connection){
            String sql = "delete from smbms_user where id =?";
            Object[] params = {delId};
            flag = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return flag;
    }

    //修改用户信息
    @Override
    public int modify(Connection connection, User user) throws Exception{
        PreparedStatement pstm = null;
        int flag = 0;
        if (null!=connection){
            String sql = "update smbms_user set user_name = ?,gender = ?,birthday = ?,phone = ?,address = ?,user_role = ?,modify_by = ?,modify_date = ? where id = ?";
            Object[] params = {user.getUserName(), user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserRole(), user.getModifyBy(), user.getModifyDate(), user.getId()};
            flag = BaseDao.execute(connection,pstm,sql,params);
            BaseDao.closeResourse(null,pstm,null);
        }
        return flag;
    }

    //通过userId获取user
    @Override
    public User getUserById(Connection connection, String id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;
        if (null != connection){
            String sql = "select u.*,r.role_name as userRoleName from smbms_user u,smbms_role r where u.id =? and u.user_role = r.id";
            Object[] params = {id};
            rs = BaseDao.execute(connection,pstm,sql,params,rs);
            if (rs.next()){
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
                user.setUserRoleName(rs.getString("userRoleName"));
            }
            BaseDao.closeResourse(null,pstm,rs);

        }
        return user;
    }
}
