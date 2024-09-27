package com.lxn.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.lxn.pojo.Role;
import com.lxn.pojo.User;
import com.lxn.service.role.RoleService;
import com.lxn.service.role.RoleServiceImpl;
import com.lxn.service.user.UserService;
import com.lxn.service.user.UserServiceImpl;
import com.lxn.util.Constants;
import com.lxn.util.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("savepwd".equals(method)){
            this.updatePwd(req,resp);
        }else if ("pwdmodify".equals(method)){
            this.getPwdByUserId(req, resp);
        }else if ("query".equals(method)) {
            this.query(req,resp);
        }else if("getrolelist".equals(method)){
            this.getRoleList(req, resp);
        }else if ("add".equals(method)){
            this.add(req, resp);
        }else if ("ucexist".equals(method)) {
            this.userCodeExist(req, resp);
        }else if ("deluser".equals(method)) {
            this.delUser(req, resp);
        }else if ("modifyexe".equals(method)) {
            this.modify(req,resp);
        } else if ("modify".equals(method)) {
            this.getUserById(req,resp,"usermodify.jsp");
        } else if ("view".equals(method)) {
            this.getUserById(req,resp,"userview.jsp");
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
    private void getPwdByUserId(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String,String> resultMap = new HashMap<>();

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

    private void query(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        //查询用户列表
        //从前端获取数据
        /* getParameter() 方法总是返回字符串类型 (String) 的数据。
        即使前端传递的是数字或者其他类型的数据，getParameter() 仍然会以 String 的形式接收 */
        String queryUserName = request.getParameter("queryname");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");

        UserService userService = new UserServiceImpl();
        List<User> userList = null;

        //默认角色
        int queryUserRole = 0;

        //第一次走这个请求，一定是第一页，页面大小是固定的
        //设置页面容量
        int pageSize = Constants.pageSize;
        //默认当前页码
        int currentPageNo = 1;

        //假如不输入用户名，此时queryUserName就会是null,不进行这个判空操作后续可能导致空指针异常
        if (queryUserName == null){
            //将queryUserName设为 ""（空字符串）是为了确保即使用户没有输入查询用户名，程序也可以正常执行查询，查找所有用户
            queryUserName = "";
        }
        //前端是下拉框选择角色，所以不会传null；如果是空字符串，等于没有选择特定角色
        //如果前端选择了角色就会通过
        if (temp !=null && !temp.isEmpty()){
            //将String转化为int
            queryUserRole = Integer.parseInt(temp);
        }
        if(pageIndex!=null){
            try{
                //获得当前页码
                currentPageNo = Integer.valueOf(pageIndex);
            }catch (NumberFormatException e){
                response.sendRedirect("error.jsp");
            }
        }

        //总数量
        int totalCount = userService.getUserCount(queryUserName,queryUserRole);
        //总页码
        PageSupport pages = new PageSupport();
        //设置当前页码
        pages.setCurrentPageNo(currentPageNo);
        //设置每页显示条数
        pages.setPageSize(pageSize);
        //设置总数量
        pages.setTotalCount(totalCount);

        //利用前面设置的变量，调用getTotalPageCount算总页面数量
        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        //如果页面小于第一页，就显示第一页
        if(currentPageNo < 1){
            currentPageNo = 1;
            //如果当前页面大于最后一页，当前页等于最后一页即可
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }

        //获取用户列表
        userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo,pageSize);
        request.setAttribute("userList",userList);
        //获取角色列表
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        request.setAttribute("roleList",roleList);

        request.setAttribute("queryUserName",queryUserName);
        request.setAttribute("queryUserRole",queryUserRole);
        request.setAttribute("totalPageCount",totalPageCount);
        request.setAttribute("totalCount",totalCount);
        request.setAttribute("currentPageNo",currentPageNo);

        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }


    //增加用户信息
    public boolean add(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        String userCode = request.getParameter("userCode");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setAddress(address);
        user.setGender(Integer.valueOf(gender));

        try{
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        }catch (ParseException e){
            e.printStackTrace();
        }

        user.setPhone(phone);
        user.setUserRole(Integer.valueOf(userRole));
        user.setCreateDate(new Date());
        user.setCreateBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());

        UserServiceImpl userService = new UserServiceImpl();


        if (userService.add(user)){
            response.sendRedirect(request.getContextPath() + "/jsp/user.do?method=query");
        }else {
            request.getRequestDispatcher("useradd.jsp").forward(request,response);
        }
        return false;
    }

    //获取角色列表
    private void getRoleList (HttpServletRequest request,HttpServletResponse response)throws IOException {
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        //把roleList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(roleList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //判断用户是否已经存在
    private void userCodeExist(HttpServletRequest request,HttpServletResponse response)throws IOException {
        String userCode = request.getParameter("userCode");

        HashMap<String,String> resultMap = new HashMap<>();
        if (StringUtils.isNullOrEmpty(userCode)){
            //userCode == null || userCode.equals("")
            resultMap.put("userCode","exist");
        }else {
            UserService userService = new UserServiceImpl();
            User user = userService.selectUserCodeExist(userCode);
            if (null!=user){
                resultMap.put("userCode","exist");
            }else {
                resultMap.put("userCode","notexist");
            }
        }

        //把resultMap转为json字符串以json的形式输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //通过userId删除user
    private void delUser(HttpServletRequest request,HttpServletResponse response)throws IOException {
        String id = request.getParameter("uid");
        Integer delId = 0;
        try{
            delId = Integer.parseInt(id);
        }catch(Exception e){
            e.printStackTrace();
            delId = 0;
        }
        HashMap<String,String> resultMap = new HashMap<>();
        if (delId <= 0){
            resultMap.put("delResult","noexist");
        }else {
            UserService userService = new UserServiceImpl();
            if (userService.deleteUserById(delId)){
                resultMap.put("delResult","true");
            }else {
                resultMap.put("delResult","false");
            }
        }

        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //修改用户信息
    private void modify(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        String id = request.getParameter("uid");
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String userRole = request.getParameter("userRole");

        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setUserName(userName);
        user.setGender(Integer.valueOf(gender));
        try{
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        }catch (ParseException e){
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        UserService userService = new UserServiceImpl();
        if (userService.modify(user)){
            response.sendRedirect(request.getContextPath() + "/jsp/user.do?method=query");
        }else {
            request.getRequestDispatcher("usermodify.jsp").forward(request,response);
        }
    }

    //通过userId获取user
    private void getUserById(HttpServletRequest request,HttpServletResponse response,String url) throws ServletException,IOException{
         String id = request.getParameter("uid");
         if (!StringUtils.isNullOrEmpty(id)){
             UserService userService = new UserServiceImpl();
             User user = userService.getUserById(id);
             request.setAttribute("user",user);
             request.getRequestDispatcher(url).forward(request,response);
         }
    }

}
