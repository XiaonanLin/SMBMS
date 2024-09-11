package com.lxn.servlet.user;

import com.lxn.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${21:16}
 * @description 注销Servlet
 */

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //清除用户的Constants.USER_SESSION
        req.getSession().removeAttribute(Constants.USER_SESSION);
        //然后重定向到登录界面
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
