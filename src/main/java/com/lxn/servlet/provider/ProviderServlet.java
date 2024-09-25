package com.lxn.servlet.provider;

import com.alibaba.fastjson.JSONArray;
import com.lxn.dao.BaseDao;
import com.lxn.pojo.Provider;
import com.lxn.pojo.User;
import com.lxn.service.provider.ProviderService;
import com.lxn.service.provider.ProviderServiceImpl;
import com.lxn.util.Constants;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.24} ${17:53}
 * @description 供应商Servlet
 */
public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null && method.equals("query")) {
            this.query(req, resp);
        } else if (method != null && method.equals("add")) {
            this.add(req, resp);
        } else if (method != null && method.equals("delprovider")) {
            this.delProvider(req, resp);
        } else if (method != null && method.equals("modify")) {
            this.getProviderById(req,resp, "providermodify.jsp");
        } else if (method != null && method.equals("modifysave")) {
            this.modify(req,resp);
        } else if (method != null && method.equals("view")) {
            this.getProviderById(req,resp, "providerview.jsp");
        }
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryProName = req.getParameter("queryProName");
        String queryProCode = req.getParameter("queryProCode");
        if (StringUtils.isNullOrEmpty(queryProName)){
            queryProName = "";
        }
        if (StringUtils.isNullOrEmpty(queryProCode)){
            queryProCode = "";
        }
        List<Provider> providerList = new ArrayList<>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList(queryProName,queryProCode);
        req.setAttribute("providerList", providerList);
        req.setAttribute("queryProName", queryProName);
        req.setAttribute("queryProCode", queryProCode);
        req.getRequestDispatcher("providerlist.jsp").forward(req,resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);
        provider.setProDetail(proDesc);
        provider.setCreateBy(((User) req.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setCreateDate(new Date());

        Boolean flag = false;
        ProviderService providerService = new ProviderServiceImpl();
        flag = providerService.add(provider);
        if (flag){
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        }else {
            req.getRequestDispatcher("provideradd.jsp").forward(req, resp);
        }
    }

    private void delProvider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("proid");
        HashMap<String,String> resultMap = new HashMap<>();
        if (!StringUtils.isNullOrEmpty(id)){
            ProviderService providerService = new ProviderServiceImpl();
            try{
                int flag = providerService.deleteProviderById(id);
                if(flag == 0){
                    //删除成功
                    resultMap.put("delResult","true");
                }else if (flag == -1) {
                    //删除失败
                    resultMap.put("delResult", "false");
                } else if (flag > 0) {
                    //该供应商下有订单，不能删除，返回订单数
                    resultMap.put("delResult", String.valueOf(flag));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            resultMap.put("delResult","noexist");
        }

        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //通过proId获取Provider

    public void getProviderById(HttpServletRequest request, HttpServletResponse response,String url)throws ServletException, IOException {
        String id = request.getParameter("proid");
        if (!StringUtils.isNullOrEmpty("proid")){
            ProviderService providerService = new ProviderServiceImpl();
            Provider provider = null;
            try {
                provider = providerService.getProviderById(id);
            }catch (Exception e){
                e.printStackTrace();
            }
            request.setAttribute("provider",provider);
            request.getRequestDispatcher(url).forward(request,response);
        }
    }

    public void modify(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String id = request.getParameter("id");
        String proName = request.getParameter("proName");
        String proContact = request.getParameter("proContact");
        String proPhone = request.getParameter("proPhone");
        String proAddress = request.getParameter("proAddress");
        String proFax = request.getParameter("proFax");
        String proDetail = request.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setId(Integer.valueOf(id));

        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProFax(proFax);
        provider.setProAddress(proAddress);
        provider.setProDetail(proDetail);

        provider.setModifyBy(((User)request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        boolean flag = false;

        ProviderService providerService = new ProviderServiceImpl();
        try{
            flag = providerService.modify(provider);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(flag);
        if (flag){
            response.sendRedirect(request.getContextPath() + "/jsp/provider.do?method=query");
        }else {
            request.getRequestDispatcher("providermodify.jsp").forward(request,response);
        }


    }
}
