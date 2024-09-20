package com.lxn.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.lxn.pojo.User;
import com.lxn.service.user.UserService;
import com.lxn.service.user.UserServiceImpl;
import com.lxn.util.Constants;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method!=null && method.equals("savepwd")){
            this.updatePwd(req,resp);
        }else if (method!=null && method.equals("pwdmodify")){
            this.getPwdByUserId(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    //修改密码
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if (o!=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)o).getId(),newpassword);
            if (flag){
                req.setAttribute(Constants.SYS_MESSAGE,"修改密码成功，请退出并使用新密码重新登录");
                //session注销
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute(Constants.SYS_MESSAGE,"修改密码失败");
            }
        }else {
            req.setAttribute(Constants.SYS_MESSAGE,"修改密码失败！");
        }
        //修改成功后进行转发
        req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req,resp);
    }

    //校对输入的旧密码正确
    private void getPwdByUserId(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String,String> resultMap = new HashMap();

        if (null == o){
            //session过期
            resultMap.put("result","sessionerror");
        }else if (StringUtils.isNullOrEmpty(oldpassword)){
            //旧密码输入为空
            resultMap.put("result","error");
        }else {
            String sessionPwd = ((User) o).getUserPassword();
            if (oldpassword.equals(sessionPwd)){
                //旧密码输入正确
                resultMap.put("result","true");
            }else {
                //旧密码输入错误
                resultMap.put("result","false");
            }
        }
        /*
          1.AJAX 请求：前端发起 AJAX 请求，后端接收并处理请求。
          2.响应数据：后端通过 PrintWriter 将处理结果以 JSON 格式写入响应流。
          3.数据接收：AJAX 的 success 回调接收到的 data 就是后端写入的 JSON 数据。
        */
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
}
