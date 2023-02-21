package com.dgut.servlet;

import com.dgut.dao.*;
import com.dgut.entity.*;

import java.io.IOException;
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
        List<User> salespersonList = userDao.findAllSalesperson();  // 从数据库或其他地方获取销售员信息
        request.setAttribute("salespersonList", salespersonList);
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