package com.dgut.servlet;
import com.dgut.dao.*;
import com.dgut.entity.*;

import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatePurchaseOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单参数
        int productId = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Date purchaseDate = Date.valueOf(request.getParameter("purchase_date"));

        // 创建进货单
//        PurchaseOrder order = new PurchaseOrder(productId, quantity, purchaseDate);
        PurchaseOrderDao  purchaseOrderDao=new PurchaseOrderDao();
        StockDaoImpl stockDao = new StockDaoImpl();
        GoodsDaoImpl goodsDao =new GoodsDaoImpl();
        Stock stock = null;
        try {
//            if (goodsDao.findById(productId).getStock()==null)

                stock = stockDao.findStockByPId(productId);
            if (stock == null) {
                throw new IllegalArgumentException("找不到商品");
            } else {
                stock.setQuantity(stock.getQuantity() + quantity);
                stockDao.updateStock(stock);
            }
            purchaseOrderDao.insertPurchaseOrder(productId, quantity, purchaseDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
