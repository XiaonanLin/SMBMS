package com.lxn.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.9} ${23:56}
 * @description User实体类
 */

//操作数据库的公共类
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态代码块，类加载的时候就初始化了
    static {
        //通过类加载器读取对应的资源
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try{
            properties.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

    }

    //获取数据库的连接
    public static Connection getConnection(){
        Connection connection = null;
        try{
            /*
            Class.forName() 方法要求JVM查找并加载指定的类到内存中，
            此时将"com.mysql.jdbc.Driver" 当做参数传入，就是告诉JVM，去"com.mysql.jdbc"这个路径下找Driver类，将其加载到内存中。
            作用：将mysql驱动注册到DriverManager中去
            */
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    //编写查询公共方法
    public static ResultSet execute(Connection connection, String sql, Object[] params, ResultSet resultSet, PreparedStatement preparedStatement){
        try{
            preparedStatement = connection.prepareStatement(sql);

            for (int i=0;i<params.length;i++){
                //setObject,占位符从1开始，但是我们的数组是从0开始
                preparedStatement.setObject(i+1,params[i]);
            }

            resultSet = preparedStatement.executeQuery();

            return resultSet;
        }catch (Exception e){
            e.printStackTrace();
            return resultSet;
        }
    }

    //编写增删改公共方法
    public static int execute(Connection connection, String sql, Object[] params,PreparedStatement preparedStatement){
        try{
            preparedStatement = connection.prepareStatement(sql);

            for (int i=0;i<params.length;i++){
                //setObject,占位符从1开始，但是我们的数组是从0开始
                preparedStatement.setObject(i+1,params[i]);
            }

            int updateRows = preparedStatement.executeUpdate();

            return updateRows;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //释放资源
    public static boolean closeResourse(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;

        if(resultSet!=null){
            try{
                resultSet.close();
                //GC回收
                resultSet = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }

        if(preparedStatement!=null){
            try{
                preparedStatement.close();
                //GC回收
                preparedStatement = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }

        if(connection!=null){
            try{
                connection.close();
                //GC回收
                connection = null;
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }
}
