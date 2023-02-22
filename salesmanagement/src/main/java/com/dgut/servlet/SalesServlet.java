package com.dgut.servlet;
import com.dgut.dao.*;

import com.dgut.entity.Contract;
import com.dgut.entity.Goods;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesServlet extends HttpServlet {
    private SalesDao salesDao;

    public void init() {
        salesDao = new SalesDao();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取查询参数


        // 查询不同客户的销售额
        Map<String, Double> salesByCustomer = null;
        try {
            salesByCustomer = salesDao.getSalesByCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 将Map对象转换为JSON格式的字符串
        Gson gson = new Gson();
        String json = gson.toJson(salesByCustomer);

        // 设置响应的内容类型为JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 将JSON字符串写入响应输出流
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
