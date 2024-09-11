package com.lxn.filter;

import com.lxn.pojo.User;
import com.lxn.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.11} ${21:16}
 * @description 权限过滤器
 */
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //过滤器，从Session中获取用户
        User userSession = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        //如果Session里面没有有用户信息
        if(null==userSession){
            //没有就重定向到错误页面
            resp.sendRedirect("/SMBMS/error.jsp");
        }else {
            //有的话让Filter继续下去
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
