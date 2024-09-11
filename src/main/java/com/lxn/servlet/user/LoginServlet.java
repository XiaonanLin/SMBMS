package com.lxn.servlet.user;

import com.lxn.pojo.User;
import com.lxn.service.user.UserService;
import com.lxn.service.user.UserServiceImpl;
import com.lxn.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${00:26}
 * @description 用户Servlet
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户编码和用户密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //调用service方法，进行用户匹配
        UserService userService = new UserServiceImpl();
        //Dao层得到对象返回给Service层，Service再返回给Servlet
        User user = userService.login(userCode,userPassword);
        //如果用户存在
        if (null!=user){
            //登录成功
            //将用户信息（即user对象）存入Session
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //页面重定向
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            //页面转发（login.jsp）带出提示信息--转发
            req.setAttribute("error","用户名或密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
