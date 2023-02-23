package com.dgut.servlet;

import com.dgut.dao.ContractDao;
import com.dgut.dao.ContractDaoImpl;
import com.dgut.dao.GoodsDaoImpl;
import com.dgut.entity.Contract;
import com.dgut.entity.Goods;
import com.dgut.entity.PurchaseList;
import com.dgut.entity.PurchaseListItem;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/contract/submit")
public class ContractSubmitServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 读取表单数据
        String customerId = request.getParameter("customer_id");
        String salespersonId = request.getParameter("salesperson_id");
        Date startDate = Date.valueOf(request.getParameter("start_date"));
        Date endDate = Date.valueOf(request.getParameter("end_date"));
        String status = request.getParameter("status");

        // 创建合同对象
        Contract contract = new Contract();
        contract.setCustomerId(Integer.parseInt(customerId));
        contract.setSalespersonId(Integer.parseInt(salespersonId));
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setStatus(status);

        // 获取商品信息
        List<PurchaseListItem> purchaseListItems = new ArrayList<>();
        // 获取所有被选中的商品
        String[] selectedGoodsIndex = request.getParameterValues("selectedGoodsIndex");
        String[] goodsCount = request.getParameterValues("goodsCount");
        Double totalAmount = 0.0;
        if (selectedGoodsIndex != null) {
            // 循环遍历所有被选中的商品
            for (String index : selectedGoodsIndex) {
                int i = Integer.parseInt(index);
                GoodsDaoImpl goodsDao = new GoodsDaoImpl();
                List<Goods> goodsList = goodsDao.findAll();
                Goods goods = goodsList.get(i);
//                int quantity = Integer.parseInt(request.getParameter("goodsCount" + i));
                int quantity = Integer.parseInt(goodsCount[i]);
                double price = goods.getPrice();
                double subtotal = price * quantity;
                totalAmount += subtotal;
                PurchaseListItem purchaseListItem = new PurchaseListItem();
//                purchaseListItem.setGoods(goods);
                purchaseListItem.setGoodsId(goods.getId());
                purchaseListItem.setQuantity(quantity);
                purchaseListItem.setSubtotal(subtotal);
                purchaseListItems.add(purchaseListItem);
            }

            // 创建合同

            contract.setAmount(totalAmount);

            // 创建采购清单
            PurchaseList purchaseList = new PurchaseList();
            contract.setPurchaseListId(purchaseList.getId());
//            purchaseList.setContract(contract);
            purchaseList.setPurchaseListItems(purchaseListItems);


            try {
                ContractDaoImpl contractDaoImpl = new ContractDaoImpl();
                contractDaoImpl.createContractAndPurchaseList(contract, purchaseList, purchaseListItems);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

// 重定向到合同列表页面
            response.sendRedirect(request.getContextPath() + "/creatContract.jsp");

        }
    }
}