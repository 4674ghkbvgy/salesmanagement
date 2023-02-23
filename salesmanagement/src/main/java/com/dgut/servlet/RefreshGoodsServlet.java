package com.dgut.servlet;

import com.dgut.dao.*;
import com.dgut.entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshGoodsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GoodsDaoImpl goodsDao = new GoodsDaoImpl();
        List<Goods> goodsList = goodsDao.findAll();
        request.setAttribute("goodsList", goodsList);

        UserDao userDao= new UserDao();
        List<User> salespersonList = userDao.findAllSalesperson();  // 获取销售员信息
        request.setAttribute("salespersonList", salespersonList);


        List<User> UserList = userDao.findAllUser();  //获取销售员信息
        request.setAttribute("userList", UserList);
//        request.getRequestDispatcher("./index.jsp").forward(request, response);

        ContractDaoImpl contractDaoImpl =new ContractDaoImpl();
        List<Contract> contractList = contractDaoImpl.findAll();
        request.setAttribute("contractList", contractList);


//        ContractDaoImpl contractDao = new ContractDaoImpl();
//        PurchaseListDao purchaseListItemDao = new PurchaseListDao();

        PurchaseOrderDao purchaseOrderDao=new PurchaseOrderDao();
        try {
            List<PurchaseOrder> PurchaseOrderList =purchaseOrderDao.findAll();
            request.setAttribute("PurchaseOrderList", PurchaseOrderList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Map<String, Double> salesByCustomer = null;
        try {
            SalesDao salesDao=new SalesDao();
            salesByCustomer = salesDao.getSalesByCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
// 将Map对象转换为JSON格式的字符串
        JsonArray jsonArray = new JsonArray();
        for (Map.Entry<String, Double> entry : salesByCustomer.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", entry.getKey());
            jsonObject.addProperty("sales", entry.getValue());
            jsonArray.add(jsonObject);
        }

        Gson gson = new Gson();
        String json = gson.toJson(jsonArray);
        request.setAttribute("SalesByCustomer", json);

        Map<String, Double> salesByGoods = null;
        try {
            SalesDao salesDao=new SalesDao();
            salesByCustomer = salesDao.getSalesByGoods();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
// 将Map对象转换为JSON格式的字符串

        JsonArray jsonArray2 = new JsonArray();
        for (Map.Entry<String, Double> entry : salesByCustomer.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", entry.getKey());
            jsonObject.addProperty("sales", entry.getValue());
            jsonArray2.add(jsonObject);
        }

        String json2 = gson.toJson(jsonArray2);
        request.setAttribute("SalesByGoods", json2);




//         将Map对象转换为JSON格式的字符串
//        Gson gson = new Gson();
//        String json = gson.toJson(salesByCustomer);
//        request.setAttribute("SalesByCustomer", json );
        // 设置响应的内容类型为JSON
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        // 将JSON字符串写入响应输出流
//        PrintWriter out = response.getWriter();
//        out.print(json);
//        out.flush();




//        Contract contract = contractDao.getContractById(1);
//
//        if (contract == null) {
//            response.setStatus(404);
//            response.getWriter().println("Contract not found");
//            return;
//        }
//
//        List<PurchaseListItem> purchaseListItems = null;
//        try {
//            purchaseListItems = purchaseListItemDao.getPurchaseListItemsByPurchaseListId(contract.getPurchaseListId());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        Map<Integer, Integer> goodsMap = new HashMap<>();
//
//        for (PurchaseListItem item : purchaseListItems) {
//            goodsMap.put(item.getGoodsId(), item.getQuantity());
//        }
//
//        request.setAttribute("goodsMap", goodsMap);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}