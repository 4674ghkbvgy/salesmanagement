package com.dgut.servlet;
import com.dgut.dao.*;

import com.dgut.entity.Contract;
import com.dgut.entity.Goods;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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

public class ContractsServlet extends HttpServlet {
    private final ContractDaoImpl contractDao = new ContractDaoImpl();
    private final PurchaseListDao purchaseListItemDao = new PurchaseListDao();
    private final GoodsDaoImpl goodsDao = new GoodsDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String requestURI = request.getRequestURI();
//        String id = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        int contractId = Integer.parseInt(requestURI.substring(requestURI.lastIndexOf("/") + 1));
//        int contractId = Integer.parseInt(request.getParameter("contractId"));
        Contract contract = contractDao.getContractById(contractId);

        if (contract == null) {
            response.setStatus(404);
            response.getWriter().println("Contract not found");
            return;
        }

        List<PurchaseListItem> purchaseListItems = null;
        try {
            purchaseListItems = purchaseListItemDao.getPurchaseListItemsByPurchaseListId(contract.getPurchaseListId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, Integer> goodsMap = new HashMap<>();

        for (PurchaseListItem item : purchaseListItems) {
            goodsMap.put(item.getGoodsId(), item.getQuantity());
        }

        List<Goods> goodsList = goodsDao.findAll();
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("goodsMap", goodsMap);
        request.setAttribute("contract", contract);
        List<Contract> contractList = contractDao.findAll();
        request.setAttribute("contractList", contractList);


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(goodsMap); // 转换成JSON格式
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
