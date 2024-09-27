package com.lxn.servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.lxn.pojo.Bill;
import com.lxn.pojo.Provider;
import com.lxn.pojo.User;
import com.lxn.service.bill.BillService;
import com.lxn.service.bill.BillServiceImpl;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author ${Linxiaonan}
 * @create ${2024.9.25} ${23:27}
 * @description 订单Servlet
 */
public class BillServlet extends HttpServlet {

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
        } else if (method != null && method.equals("getproviderlist")) {
            this.getProviderlist(req, resp);
        } else if (method != null && method.equals("delbill")) {
            this.delBill(req, resp);
        } else if (method != null && method.equals("view")) {
            this.getBillById(req, resp, "billview.jsp");
        }else if (method != null && method.equals("modify")) {
            this.getBillById(req, resp, "billmodify.jsp");
        } else if (method != null && method.equals("modifysave")) {
            this.modify(req, resp);
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");
        request.setAttribute("providerList", providerList);

        String queryProductName = request.getParameter("queryProductName");
        String queryProviderId = request.getParameter("queryProviderId");
        String queryIsPayment = request.getParameter("queryIsPayment");
        if (StringUtils.isNullOrEmpty(queryProductName)) {
            queryProductName = "";
        }

        List<Bill> billList = new ArrayList<Bill>();
        BillService billService = new BillServiceImpl();
        Bill bill = new Bill();

        if (StringUtils.isNullOrEmpty(queryIsPayment)) {
            bill.setIsPayment(0);
        } else {
            bill.setIsPayment(Integer.parseInt(queryIsPayment));
        }

        if (StringUtils.isNullOrEmpty(queryProviderId)) {
            bill.setProviderId(0);
        } else {
            bill.setProviderId(Integer.parseInt(queryProviderId));
        }

        bill.setProductName(queryProductName);
        System.out.println(bill);
        System.out.println(queryProductName);
        System.out.println(queryProviderId);
        System.out.println(queryIsPayment);

        billList = billService.getBillList(bill);
        System.out.println(billList);
        request.setAttribute("billList", billList);
        request.setAttribute("queryProductName", queryProductName);
        request.setAttribute("queryProviderId", queryProviderId);
        request.setAttribute("queryIsPayment", queryIsPayment);
        request.getRequestDispatcher("billlist.jsp").forward(request, response);

    }

    //增加订单
    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String billCode = request.getParameter("billCode");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");

        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDetail(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));
        bill.setCreateBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreateDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.add(bill);
        if (flag) {
            response.sendRedirect(request.getContextPath() + "/jsp/bill.do?method=query");
        } else {
            request.getRequestDispatcher("billadd.jsp").forward(request, response);
        }
    }

    //获取供应商列表
    private void getProviderlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");

        //把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //通过delId删除bill
    private void delBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        HashMap<String, String> resultMap = new HashMap<>();
        if (!StringUtils.isNullOrEmpty(id)) {
            BillService billService = new BillServiceImpl();
            boolean flag = billService.deleteBillById(id);
            if (flag) {
                resultMap.put("delResult", "true");
            } else {
                resultMap.put("delResult", "false");
            }
        }

        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    //通过id获取订单信息
    private void getBillById(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        if (!StringUtils.isNullOrEmpty(id)) {
            BillService billService = new BillServiceImpl();
            Bill bill = null;
            bill = billService.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    //修改订单信息
    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setId(Integer.valueOf(id));
        bill.setProductName(productName);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));

        bill.setModifyBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();
        flag = billService.modify(bill);
        if (flag) {
            response.sendRedirect(request.getContextPath() + "/jsp/bill.do?method=query");
        } else {
            request.getRequestDispatcher("billmodify.jsp").forward(request, response);
        }
    }
}
