

package com.dgut.servlet;

import com.dgut.dao.*;
import com.dgut.entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ContractDaoImpl contractDao;
    private PurchaseListDao purchaseListDao;
    private PaymentDao paymentDao;
    private GoodsDaoImpl goodsDao;

    public void init() {
        contractDao = new ContractDaoImpl();
        purchaseListDao = new PurchaseListDao();
        paymentDao = new PaymentDao();
        goodsDao = new GoodsDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show payment form
        int contractId = Integer.parseInt(request.getParameter("contract_id"));
        Contract contract = contractDao.getContractById(contractId);
        request.setAttribute("contract", contract);
        request.getRequestDispatcher("/paymentForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 读取表单数据
        int contractId = Integer.parseInt(request.getParameter("payment_contract_id"));
        int purchaseListId = -1;

        // 获取商品信息
        List<PurchaseListItem> purchaseListItems = new ArrayList<>();
        // 获取所有被选中的商品
        String[] selectedGoodsIndex = request.getParameterValues("selectedPaymentsIndex");
        String[] goodsCount = request.getParameterValues("paymentsCount");
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
//              purchaseListItem.setGoods(goods);
                purchaseListItem.setGoodsId(goods.getId());
                purchaseListItem.setQuantity(quantity);
                purchaseListItem.setSubtotal(subtotal);
                purchaseListItems.add(purchaseListItem);
            }
            Map<Integer, Integer> goodsMap = new HashMap<>();
            for (PurchaseListItem item : purchaseListItems) {
                goodsMap.put(item.getGoodsId(), item.getQuantity());
            }

            //检查超买
            try {
                if(!checkPurchaseListItems(purchaseListId,goodsMap))
                {
                    response.sendRedirect(request.getContextPath() + "/payTooMuch.jsp");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                purchaseListId = purchaseListDao.insertPurchaseListItem(purchaseListItems);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // 创建支付清单
            Payment payment = new Payment(contractId, purchaseListId, totalAmount, Date.valueOf(LocalDate.now()));
            PaymentDao paymentDao = new PaymentDao();
            try {
                paymentDao.insertPayment(payment);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

// 重定向到合同列表页面
            response.sendRedirect(request.getContextPath() + "/creatPayment.jsp");

        }
    }

    private boolean checkPurchaseListItems(int purchaseListId, Map<Integer, Integer> goodsMap) throws SQLException {
        List<PurchaseListItem> purchaseList = purchaseListDao.getPurchaseListItemsByPurchaseListId(purchaseListId);
        if (purchaseList == null) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : goodsMap.entrySet()) {
            int goodsId = entry.getKey();
            int quantity = entry.getValue();
            if (quantity <= 0) {
                return false;
            }
            PurchaseListItem item = purchaseListDao.getPurchaseListItemByPurchaseListIdAndGoodsId(purchaseListId, goodsId);

            if (item == null || item.getQuantity() < quantity) {
                return false;
            }
        }

        return true;
    }
}


//
//
//        // Get payment info from form
//        int contractId = Integer.parseInt(request.getParameter("contract_id"));
//        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
//        Map<Integer, Integer> goodsMap = new HashMap<>();
//        for (String key : request.getParameterMap().keySet()) {
//            if (key.startsWith("goods_")) {
//                int goodsId = Integer.parseInt(key.substring(6));
//                int quantity = Integer.parseInt(request.getParameter(key));
//                goodsMap.put(goodsId, quantity);
//            }
//        }
//
//        // Check if purchase items exist and payment amount is valid
//
////                = contractDao.getContractById(contractId).getPurchaseListId();
////        try {
////            if (!checkPurchaseListItems(purchaseListId, goodsMap)) {
////                request.setAttribute("message", "Invalid purchase items or payment amount");
////                doGet(request, response);
////                return;
////            }
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//        try {
//            purchaseListId = purchaseListDao.insertPurchaseListItem();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        // Insert new payment record
//        try {
//            paymentDao.insertPurchaseListItems(purchaseListId, goodsMap);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
////        // Update goods quantity
////        for (Map.Entry<Integer, Integer> entry : goodsMap.entrySet()) {
////            int goodsId = entry.getKey();
////            int quantity = entry.getValue();
////            goodsDao.updateGoodsQuantity(goodsId, -quantity);
////        }
//
//        response.sendRedirect(request.getContextPath() + "/contracts");
//    }
//

//
//}